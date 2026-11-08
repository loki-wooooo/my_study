import os

# openai 키 입력, OpenAI API 키를 환경 변수(os.environ)에 저장
os.environ[
    "OPEN_API_KEY"] = "sk-proj-1234111lyzhcDArcsq14TTUOjtLT00Zvg3uS5BThuNDpEhmZ54eEelQXAzp22d3lemJZbv2iXMy-MoKTjT3BlbkFJt5HBN24vLXXO9qMhGxq5kqUFWiprtAlZDclS0VwHGHPzMuGbXZ1aSRkw7stwXWKs9JHBBN778A"

from langchain_openai import ChatOpenAI
llm = ChatOpenAI(
    model = "gpt-4o",
    temperature = 0,
)

# csv 파일을 읽어 벡터로 변환 및 ChromaDB에 저장함
import pandas as pd
from langchain_openai import OpenAIEmbeddings
from langchain.vectorstores import Chroma
from langchain.llms import OpenAI
from langchain.chains import RetrievalQA

# 데이터 불러오기
file_path= 'C:\\utils\\05.work\\my_study\\backend\\03. python\\llm\\rag\\chapter05\\data\\files\\Disease_symptom_and_patient_profile_dataset.csv'
df = pd.read_csv(file_path)

# 데이터를 텍스트로 변환
df["text"] = df.apply(
    lambda row: 
        f"질병: {row['Disease']}, 증상: 발열 ({row['Fever']}), 기침 ({row['Cough']}), 피로 ({row['Fatigue']}), 호흡곤란 ({row['DifficultyBreathing']}),"
        f"연령: {row['Age']}, 성별: {row['Gender']}, 혈압: {row['Blood Pressure']}, 콜레스테롤: {row['Cholesterol Level']}, 결과: {row['Outcome Variable']}", axis=1
)

# OpenAI 임베딩 모델 생성
embeddings = OpenAIEmbeddings()

# ChromaDB에 데이터 저장
chroma_path = "C:\\data\\chroma_disease_db"
vectorstore = Chroma.from_texts(
    df['text'].tolist(),
    embeddings,
    persist_directory=chroma_path
)

# ChromaDB에서 검색할 retriever 생성
retriever = vectorstore.as_retriever(
    search_type="similarity",
    search_kwargs={"k": 5}
)

# LangChain QA 체인 설정
# RetrievalQA(검색/검색단계) 질문에 맞는 정보를 외부 저장소에서 찾아오는 과정, 요즘 AI맥락에서는 주로 RAG의 첫 단계
# Retrieval - 사용자 질의와 관련된 후보 문서나 데이터를 찾음
# Augmentation - 찾은 내용을 원래 프롬프트와 합침
# Generation - LLM이 질문과 검색된 근거를 함께 보고 답을 생성하므로, 답이 근거에 기반하고 환각이 줄어드는 경향
qa_chain = RetrievalQA.from_chain_type(
    llm=llm,
    retriever=retriever,
    chain_type="stuff" # 검색된 문서를 전부 하나의 프롬프트에 그대로 밀어 넣는 방식
)

# 사용자 질문
query = "기침과 발열이 있는 질병은 무엇인가요?"
response = qa_chain_invoke(query)

# 결과 출력
print(f"질문 : {query}")
print(f"답변 : {response}")

# 구체적 질문
query = """35세 여성이고, 기존에 혈압이 높았어, 어제부터 열이 있고 기침 증상이 있어.
무슨 질병을 의심해야 해? 단, 데이터만 참조해서 답변해줘 """
response = qa_chain_invoke(query)

# 결과 출력
print(f"질문 : {query}")
print(f"답변 : {response}")