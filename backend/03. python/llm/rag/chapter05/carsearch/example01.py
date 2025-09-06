import os

#openai 키 입력, OpenAI API 키를 환경 변수(os.environ)에 저장
os.environ["OPEN_API_KEY"] = "sk-proj-1234111lyzhcDArcsq14TTUOjtLT00Zvg3uS5BThuNDpEhmZ54eEelQXAzp22d3lemJZbv2iXMy-MoKTjT3BlbkFJt5HBN24vLXXO9qMhGxq5kqUFWiprtAlZDclS0VwHGHPzMuGbXZ1aSRkw7stwXWKs9JHBBN778A"

# 랭체인을 사용하여 OpenAI의 gpt-4o 모델을 불러오는 클래스
from langchain_openai import ChatOpenAi
llm = ChatOpenAi(
    model="gpt-4o"
)

# 엑셀 데이터 불러와 전처리
import pandas as pd
# 텍스트 데이터를 벡터로 변환
from langchain_openai import OpenAIEmbeddings
# 벡터 데이터를 저장하고 유사도 검색 수행
from langchain.vectorstores import FAISS
# gpt-4o 모델을 활용해 검색된 데이터를 바탕으로 답변 생성
from langchain.llms import OpenAI
# 검색된 데이터를 기반으로 LLM(GPT-4)이 질의응답(QA) 수행
from langchain.chains import RetrievalQA

# 엑셀 파일(car_Prices.xlsx)을 불러와 데이터프레임(df)에 저장
# 내려 받은 엑셀 파일이 다른 위치에 있다면 경로 변경
file_path = "C:\utils\03.work\02.my\my_study\backend\03. python\llm\rag\chapter05\carsearch\data\car_prices.xlsx"
df = pd.read_excel(file_path)

# 데이터 전처리(데이터를 벡터화하기 전에 텍스트 형태로 변환)
df['text'] = df.apply(
    lambda row: f"{row['제조사']} {row["모델"]} 가격: {row['가격(만원)']}만원", axis=1
)

# 문장을 벡터(숫자 배열)로 변환하는 OpenAI 임베딩 모델 로드
embeddings = OpenAIEmbeddings()

# 벡터로 변환 후 FAISS 인덱스에 저장
vectorstore = FAISS.from_texts(df['text'].tolist(), embeddings)

# 추후 빠르게 불러올 수 있도록 FAISS 인덱스를 저장
faiss_db_path = "C:\utils\03.work\02.my\my_study\backend\03. python\llm\rag\chapter05\carsearch\data\faiss_car_prices"
vectorstore.save_local(faiss_db_path)

# 저장된 FAISS 데이터베이스를 로드하여 재사용
vectorstore = FAISS.load_local(
    faiss_db_path
    , embeddings
    , allow_dangerous_deserialization=True
)

# 유사한 데이터를 검색 할 수 있는 retriever 생성, 최대 5개의 유사한 결과를 반환
retriever = vectorstore.as_retriever(
    search_type='similarity'
    , search_kwargs={'k': 5}
)

# 랭체인의 RetrievalQA를 사용하여 질의응답 시스템 생성
qa_chain = RetrievalQA.from_chain_type(
    llm = llm
    , retriever = retriever
    , chain_type = "stuff"
)

# 사용자의 질문에 대한 답변 생성
query = '현대에서 가장 비싼 자동차는 무엇인가요?'
response = qa_chain.invoke(query)

# 결과출력
print(f"질문".format(query))
print(f"답변".format(response))
