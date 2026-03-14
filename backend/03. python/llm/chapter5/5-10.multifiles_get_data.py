import os

os.environ[
    "OPENAI_API_KEY"] = "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"

from langchain_openai import ChatOpenAI

# 값의 범위는 일반적으로 [0, 1]을 갖는다. 낮은 값일수록 모델이 더 예측 가능한 출력을 생성하고, 높은 값일 수록 모델이 더 다양하고 창의적인 출력을 생성한다.
llm = ChatOpenAI(
    model_name="gpt-4o",
    temperature=0,
)

base_path = 'C:\\utils\\03.work\\01.my\\my_study\\backend\\03. python\\llm\\chapter5\\files'
all_texts = []  # 추출된 모든 텍스트 데이터를 저장할 저장소를 초기화

import pandas as pd
from langchain_community.document_loaders import PyPDFLoader
from langchain_core.documents import Document


# 워드 파일(.docx)에서 텍스트 추출
def load_docx(path):
    doc = Document(path)
    return "\n".join([p.text for p in doc.paragraphs if p.text.strip() != ''])


# doc.paragraphs 문서 내 모든 문단(paragraphss)를 반복하면서 텍스트 추출
# strip() != ''을 사용하여 공백 문단 제거
# '\n'.join(...)을 사용하여 각 문단을 줄바꿈(\n) 기준으로 하나의 문자열로 결합

# 엑셀 파일(.xlsx)에서 텍스트 추출
def load_excel(path):
    xls = pd.ExcelFile(path)
    all_sheets_texts = []
    for sheet_name in xls.sheet_names:
        df = xls.parse(sheet_name)
        all_sheets_texts.append("\n".join(map(str, df.values.ravel())))
    return "\n".join(all_sheets_texts)


# xls.sheet_names: 파일 내 모든 시트 이름을 가저옴
# xls.parse(sheet_name): 각 시트를 Pandas 데이터프레임으로 변환
# df.values.ravel(): 모든 셀 값을 1차원 배열(numpy.ravel())로 변환
# map(str, df.values.ravel()): 모든 셀 값을 문자열로 변환
# "\n".join(...): 시트 내용을 줄바꿈("\n") 기준으로 결합

# PDF 파일에서 텍스트 추출
def load_pdf(path):
    try:
        doc = PyPDFLoader(path).load()
        return "\n".join([page.page_content for page in doc])
    # PDF 파일이 손상되었거나 읽을 수 없을 경우 오류 메시지를 출력
    except Exception as e:
        print(f"Error loading PDF file {path}: {e}")
        return ""


# [page.page_content for page in doc]: 모든 페이지에서 텍스트(page_content) 추출
# "\n".join(...): 각 페이지의 텍스트를 줄바꿈(\n)을 기준으로 결합

# base_path 폴더 내 모든 파일과 하위 폴더를 탐색
for subdir, dirs, files in os.walk(base_path):
    # 파일 확장자 확인 및 적절한 함수 호출
    for file in files:
        # 파일의 전체 경로 생성(예: c:/data/files/report.pdf)
        filename = os.path.join(subdir, file)
        # 파일 확장자 추출 및 소문자로 변환
        extension = os.path.splitext(filename)[-1].lower()

        # 파일 타입에 따라 적절한 함수 호출
        text_content = ""
        try:
            if extension == ".docx":
                text_content = load_docx(filename)
            elif extension == ".xlsx":
                text_content = load_excel(filename)
            elif extension == ".pdf":
                text_content = load_pdf(filename)

        # 예외처리
        except Exception as e:
            print(f"Error: {e}")

        # 텍스트가 정상적으로 추출되었을 경우 all_texts에 추가
        if text_content:
            all_texts.append(text_content)

from langchain_community.vectorstores import FAISS
from langchain_openai import OpenAIEmbeddings
from langchain_text_splitters import RecursiveCharacterTextSplitter

# 모든 문서를 하나의 문자열로 합침
combined_text = "\n".join(all_texts)

# RecursiveCharacterTextSplitter를 사용하여 텍스트 분할
text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=50)
# 하나의 긴 문서를 여러 개의 청크로 분할
texts = text_splitter.split_text(combined_text)

# OpenAI 임베딩 모델 로드
embeddings = OpenAIEmbeddings()

# 변환된 벡터를 FAISS 벡터 데이터베이스에 저장
vectorstore = FAISS.from_texts(texts, embeddings)
# 추후 재사용을 위해 FAISS 백터 데이터를 로컬 디렉터리에 저장
vectorstore.save_local(
    "C:\\utils\\03.work\\01.my\\my_study\\backend\\03. python\\llm\\chapter5\\data\\db_faiss_combined")

# FAISS 데이터베이스 로드(FAISS.load_local)
retriever = FAISS.load_local(
    "C:\\utils\\03.work\\01.my\\my_study\\backend\\03. python\\llm\\chapter5\\data\\db_faiss_combined"
    , OpenAIEmbeddings()
    , allow_dangerous_deserialization=True  # 역직렬화(deserialization)를 허용
).as_retriever(
    # 유사도 검색으로 가장 관련성 높은 문서 8개를 조회한다
    search_type="similarity",
    search_kwargs={"k": 8}
)  # as_retriever를 사용하여 문서를 검색 가능하도록 변환

# from langchain.chains.retrieval import create_retrieval_chain
#
# #RetrievalQA.from_chain_type()을 사용하여 QA 체인 구성(이전 버전의 타입)
# qa_chain = create_retrieval_chain(
#     llm=llm
#     , chain_type="stuff"
#     , retriever=retriever # FAISS 데이터베이스에서 유사한 문서를 검색
# )
#
# # 엑셀 기반의 질문
# query = "마음 챙김의 리뷰는?"
# response = qa_chain.invoke(query)
# print(response)

from langchain_classic.chains.retrieval import create_retrieval_chain
from langchain_classic.chains.combine_documents import create_stuff_documents_chain
from langchain_core.prompts import ChatPromptTemplate


# context ->  retriever가 찾아온 문서들로 자동 치환

'''
1. 사용자가 {"input": query} 전달
2. retriever가 query를 가지고 관련 문서 검색
3. 검색된 문서들을 context로 묶음
4. prompt 안의 {context} 자리에 넣음
5. {input} 자리에는 query를 넣음
6. 완성된 프롬프트를 llm에 전달
'''
prompt = ChatPromptTemplate.from_template("""
주어진 문맥만 사용해서 질문에 답하세요.

문맥:
{context}

질문:
{input}

답변:
""")

question_answer_chain = create_stuff_documents_chain(
    llm,
    prompt
)

qa_chain = create_retrieval_chain(
    retriever,
    question_answer_chain
)

query = "마음 챙김의 리뷰는?"
response = qa_chain.invoke({"input": query})

print(response["answer"])
