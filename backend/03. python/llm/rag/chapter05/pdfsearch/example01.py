import os

# openai 키 입력, OpenAI API 키를 환경 변수(os.environ)에 저장
os.environ[
    "OPEN_API_KEY"] = "sk-proj-1234111lyzhcDArcsq14TTUOjtLT00Zvg3uS5BThuNDpEhmZ54eEelQXAzp22d3lemJZbv2iXMy-MoKTjT3BlbkFJt5HBN24vLXXO9qMhGxq5kqUFWiprtAlZDclS0VwHGHPzMuGbXZ1aSRkw7stwXWKs9JHBBN778A"

from langchain_openai import ChatOpenAI
llm = ChatOpenAI(
    model="gpt-4o",
)

from langchain.document_loaders import PyPDFLoader
docs = [] # 추출된 문서를 저장할 리스트를 초기화

# d:/data/succulent.pdf 경로의 PDF 파일을 PyPDFLoader로 로드
loader = PyPDFLoader("../data/succulent.pdf")

# PDF에서 텍스트 데이터를 추출하여 텍스트 데이터로 변환
text_content = loader.load()

# PDF의 각 페이지를 하나씩 가져와 docs에 저장
for doc in text_content:
    docs.append(doc)

# 첫 번째 페이지의 처음 100자 출력
print(docs[0].page_content[:100])
# 첫 번째 문서(첫 페이지)의 메타데이터 출력
print(docs[0].metadata)

'''
    FAISS를 이용해서 텍스트 데이터를 백터화 및 검색 할 수 있도록 FAISS 구축

    - RecursiveCharacterTextSplitter
        - 문단/문장 경계 보존이 중요할 때
        - 웹/문서 크롤링처럼 서식이 들쭉날쭉한 데이터
        - RAG 임베딩 품질을 우선할 때(권장 기본값)

    - CharacterTextSplitter
        - 내부 형식이 매우 단순하거나
        - 성능과 단순성이 더 중요하고, 경계 품질 요구가 낮을 때
'''

from langchain_openai import OpenAIEmbeddings
from langchain import FAISS
from langchain.text_splitter import CharacterTextSplitter

text_splitter = CharacterTextSplitter(
    separator="\n", # 두 줄 띄움(\n\n)을 기준으로 분할
    chunk_size=100, # 긴 텍스트를 100자 단위로 분할
    chunk_overlap=10, # 각 청크 사이에 10자 겹치도록 설정
    length_function=len,
    is_separator_regex=False
)

# text_content에서 텍스트를 가져와 분할
splits = text_splitter.split_documents(text_content)

# 문서를 FAISS가 처리할 수 있도록, 텍스트(page_content)만 추출해서 texts에 저장
texts = [doc.page_content for doc in splits]

# OpenAI 임베딩 모델 로드
embeddings = OpenAIEmbeddings()

# 변환된 벡터를 FAISS 벡터 데이터베이스에 저장
vectorstore = FAISS.from_texts(texts, embeddings)

# FAISS 벡터 인덱스를 로컬 파일(../data/db_faiss_complete_openai)에 저장
vectorstore.save_local("../data/db_faiss_complete_openai")

from langchain_core.prompts import PromptTemplate
# PromptTemplate를 사용하여 맞춤형 프롬프트 생성
custom_prompt_template = PromptTemplate(
    template="""당신은 다육이를 키우는 정보를 제공하는 챗봇입니다.
    {context}    
        
    제공된 소스 데이터만 사용하여 고객의 질문에 답하세요.
    모르겠다면 "잘 모르겠습니다."라고 답하세요.
    친절하고 예의 바르며 전문적인 어조를 사용하세요.
    답변은 간결하게 유지하세요.
    
    질문: {question}
    
    답변:
    """,
    input_variables=["context"]
)
# 프롬프트를 랭체인 체인(chain_type_kwargs)에 전달
chain_type_kwargs = {"prompt": custom_prompt_template}

# FAISS 데이터베이스 로드(FAISS.load_local)
retriever = FAISS.load_local(
    "../data/db_faiss_complete_openai",
    OpenAIEmbeddings(),
    chain_type_kwargs=chain_type_kwargs
).as_retriever(
    search_type="similarity" # 유사한 문서를 검색
    , search_kwargs={"k": 8} # 최대 8개의 유사한 문서를 반환
) # as_retriever()를 사용하여 문서를 검색 가능하도록 변환

# 검색과 질의응답(QA)을 결합한 랭체인 체인
from langchain.chains import RetrievalQA

qa_chain = RetrievalQA.from_chain_type(
    llm=llm
    , chain_type="stuff" # 검색된 문서를 합쳐 하나의 입력(prompt)으로 전달
    , retriever=retriever #FAISS 기반의 관련 문서 검색
    , chain_type_kwargs=chain_type_kwargs # 프롬프트(PromptTemplate) wjrdyd
)

# 사용자의 질문을 입력하여 검색 및 응답 생성
query = "겨울철 다육이 키우는 방법은?"
response = qa_chain.invoke(query)

print(response)