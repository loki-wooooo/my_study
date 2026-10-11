import os

# openai 키 입력, OpenAI API 키를 환경 변수(os.environ)에 저장
os.environ[
    "OPEN_API_KEY"] = "sk-proj-1234111lyzhcDArcsq14TTUOjtLT00Zvg3uS5BThuNDpEhmZ54eEelQXAzp22d3lemJZbv2iXMy-MoKTjT3BlbkFJt5HBN24vLXXO9qMhGxq5kqUFWiprtAlZDclS0VwHGHPzMuGbXZ1aSRkw7stwXWKs9JHBBN778A"

from langchain_openai import ChatOpenAI
llm = ChatOpenAI(
    model="gpt-4o",
    temperature= 0,
)

# 파일들이 위차한 경로와 테스트 데이터가 담길 저장소를 초기화
base_path = 'C:\\utils\\05.work\\my_study\\backend\\03. python\\llm\\rag\\chapter05\\data\\files'
all_texts = [] # 추출된 모든 텍스트 데이터를 저장할 저장소를 초기화

import pandas as pd #데이터를 읽고, 정리하고, 변환하고, 분석하는 작업을 효율적으로 수행하도록 만들어진 도구
from langchain_community.document_loaders import PyPDFLoader
from docx import Document

# 워드 파일(.docx)에서 텍스트 추출
def load_docx(path):
    doc = Document(path)
    return "\n".join([p.text for p in doc.paragraphs if p.text.strip() != ''])

# doc.paragraphs로 문서 내 모든 문단(paragraphs)을 반복하면서 텍스트 추출
# strip() != ''을 사용하여 공백 문단 제거
# "\n".join(...)을 사용하여 각 문단을 줄바꿈(\n) 기준으로 하나의 문자열로 집합

# 엑셀 파일(.xlsx)에서 텍스트 추출
def load_excel(path):
    xls = pd.ExcelFile(path)
    all_sheets_texts = []
    for sheet_name in xls.sheet_names:
        df = pd.parse(sheet_name)
        all_sheets_texts.append("\n".join(map(str, df.values.ravel())))
    return "\n".join(all_sheets_texts)

# xls.sheet_names: 파일 내 모든 시트 이름을 가져옴
# xls.parse(sheet_name): 각 시트를 데이터프레임으로 변환
# df.values.ravel(): 데이터프레임의 모든 값을 1차원 배열로 변환
# map(str, ...): 모든 셀 값을 문자열로 변환
# "\n".join(...): 시트 내용을 줄바꿈(\n) 기준으로 결합


# PDF 파일(.pdf)에서 텍스트 추출
def load_pdf(path):
    try:
        doc = PyPDFLoader(path).load()
        return "\n".join([page.page_content for page in doc])
    # PDF 파일이 손상되었거나 읽을 수 없을 경우 오류 메시지를 출력
    except Exception as e:
        print(f"Error loading PDF file {path}: {e}")
        return ""

# [page.page_content for page in doc]: 모든 페이지에서 텍스트(page_content)를 추출
# "\n".join(...): 페이지 내용을 줄바꿈(\n) 기준으로 결합

## 텍스트를 추출함
# base_path 폴더 내 모든 파일과 하위 폴더를 탐색
for subdir, dirs, files in os.walk(base_path):
    # 파일 확장자 확인 및 적절한 함수 호출
    for file in files:
        # 파일의 전체 경로 생성(예: d:/data/files/report.pdf)
        file_path = os.path.join(subdir, file)
        
        # 파일 확장자 추출 및 소문자로 변환
        extension = os.path.splitext(file)[1].lower()
        
        # 파일 타입에 따라 적절한 함수 호출
        text_content = ""
        try:
            if extension == '.docx':
                text_content = load_docx(file_path)
            elif extension == '.xlsx':
                text_content = load_excel(file_path)
            elif extension == '.pdf':
                text_content = load_pdf(file_path)
        except Exception as e:
            print(f"Error processing file {file_path}: {e}")
            
        # 텍스트가 정상적으로 추출되었을 경우 all_texts에 추가
        if text_content:
            all_texts.append(text_content)
            
            
## 임베딩 모델을 이용하여 문서를 벡터화한 후, 이를 검색할 수 있도록 벡터 데이터베이스를 구축
from langchain import FAISS
from langchain.embeddings import OpenAIEmbeddings
from langchain.text_splitter import RecursiveCharacterTextSplitter

# 모든 문서를 하나의 문자열로 합침
combined_text = "\n".join(all_texts)

# RecursiveCharacterTextSplitter를 사용하여 텍스트 분할
text_splitter = RecursiveCharacterTextSplitter(
    chunk_size=500,  # 각 청크의 최대 길이
    chunk_overlap=50,  # 청크 간 겹침 길이
)

# 하나의 긴 문서를 여러 개의 청크로 분할
texts = text_splitter.split_text(combined_text)

# OpenAI 임베딩 모델 로드
embeddings = OpenAIEmbeddings()

# 변환된 벡터를 FAISS 벡터 데이터베이스에 저장
vectorstore = FAISS.from_texts(texts, embeddings)
vectorstore.save_local("C:\\utils\\05.work\\my_study\\backend\\03. python\\llm\\rag\\chapter05\\data\\files\\db_faiss_combined") # 로컬에 벡터 데이터베이스 저장

# FAISS 데이터베이스 로드(FAISS.load_local())
retriever = FAISS.load_local(
    "C:\\utils\\05.work\\my_study\\backend\\03. python\\llm\\rag\\chapter05\\data\\files\\db_faiss_combined",
    embeddings,
    allow_dangerous_deserialization=True # 역직렬화 유/무
    ).as_retriever(
        search_type="similarity",  # 검색 유형 설정: 유사도 기반 검색
        search_kwargs={"k": 8}  # 검색 시 반환할 상위 k개의 결과 설정
    ) # as_retriever()를 사용하여 검색기(retriever)로 변환
    
from langchain.chains import RetrievalQA

# RetrievalQA 체인을 생성하여 검색기(retriever)와 LLM을 연결
qa_chain = RetrievalQA.from_chain_type(
    llm=llm,  # LLM 모델 설정
    chain_type="stuff",  # 체인 유형 설정
    retriever=retriever,  # 검색기(retriever) 설정
)

# example
# 엑셀 기반의 질문
query = "마음 챙김의 리뷰는?"
response = qa_chain.invoke(query)
print(response)
