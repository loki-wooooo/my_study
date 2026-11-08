import os

# openai 키 입력, OpenAI API 키를 환경 변수(os.environ)에 저장
os.environ[
    "OPEN_API_KEY"] = "sk-proj-1234111lyzhcDArcsq14TTUOjtLT00Zvg3uS5BThuNDpEhmZ54eEelQXAzp22d3lemJZbv2iXMy-MoKTjT3BlbkFJt5HBN24vLXXO9qMhGxq5kqUFWiprtAlZDclS0VwHGHPzMuGbXZ1aSRkw7stwXWKs9JHBBN778A"

from langchain_openai import ChatOpenAI
llm = ChatOpenAI(
    model = "gpt-4o",
    temperature = 0,
)

from langchain_community.document_loaders import CSVLoader

# CSV 파일 가져오기
loader = CSVLoader('rag/chapter05/unsuitable/football.csv')
docs = loader.load()
docs[0] # 첫 번째 문서를 가저옴

from langchain.text_splitter import CharacterTextSplitter

# 문서 분할 기준 설정
text_splitter = CharacterTextSplitter(chunk_size=1000, chunk_overlap=0)

# 문서를 청크로 나누기
docs = text_splitter.split_documents(docs)

# OpenAI의 임베딩 모델 사용
from langchain_openai import OpenAIEmbeddings
embedding_function = OpenAIEmbeddings()

from langchain.vectorstores import Chroma
# Docs를 embedding_function을 사용하여 백터로 변환
db = Chroma.from_documents(docs, embedding_function)

# 프롬프트 템플릿 정의
template = """당신은 축구에 대한 정보를 제공하는 챗봇입니다.

{context}

제공된 소스 데이터만 사용하여 고객의 질문에 답하세요.
모르겠다면 "잘 모르겠습니다."라고 답하세요.
친절하고 예의 바르며 전문적인 어조를 사용하세요.
답변은 간결하게 유지하세요.

질문:

답변: """

from langchain.prompts import PromptTemplate
# PromptTemplate을 사용하여 프롬프트 초기화
prompt = PromptTemplate(template=template, input_variables=["context"])
# input_variables=["context"]: 프롬프트에서 사용할 변수(context)를 정의

from langchain.chains import RetrievalQA
# RetrievalQA 체인에 사용할 프롬프트 설정
chain_type_kwargs = {"prompt": prompt}

# RetrievalQA 체인 생성
chain = RetrievalQA.from_chain_type(
    llm=llm,
    chain_type="stuff",
    retriever=db.as_retriever(search_kwargs={"k": 10}),
    # 프롬프트 설정을 적용하여 응답 스타일 지정
    chain_type_kwargs=chain_type_kwargs,
)

import textwrap
# 80자 단위 자동 줄 바꿈으로 긴 텍스트를 보기 좋게 하는 함수
def print_response(response: str):
    print("\n".join(textwrap.wrap(response, width=80)))
    
query = "L. Messi의 소속팀은?" # 사용자 정의
response = chain.invoke(query)

response