# 변경전 = 랭체인 사용

import os

os.environ[
    "OPENAI_API_KEY"] = "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"

from langchain_openai import ChatOpenAI

llm = ChatOpenAI(
    model_name="gpt-4o",
    temperature=0,
)

import pandas as pd
from langchain_openai import OpenAIEmbeddings
from langchain_community.vectorstores import Chroma
from langchain_openai import ChatOpenAI
from langchain_classic.chains.retrieval import create_retrieval_chain
from langchain_classic.chains.combine_documents import create_stuff_documents_chain
from langchain_core.prompts import ChatPromptTemplate

# 데이터 불러오기
file_path = "C:\\utils\\03.work\\01.my\\my_study\\backend\\03. python\\llm\\chapter5\\data\\Disease_symptom_and_patient_profile_dataset.CSV"
df = pd.read_csv(file_path)

# 데이터를 텍스트로 변환
df["text"] = df.apply(
    lambda row: f"질병: {row['Disease']}, 증상: {row['Symptom']}, 환자 프로필: {row['Patient Profile']}", axis=1
)

# OepnAI 임베딩 모델 생성
embeddings = OpenAIEmbeddings()

# ChromaDB에 데이터 저장
chroma_path = "C:\\utils\\03.work\\01.my\\my_study\\backend\\03. python\\llm\\chapter5\\data\\chroma_disease_db"
vectorstore = Chroma.from_texts(
    df["text"].tolist(),
    embeddings,
    persist_directory=chroma_path
)

# ChromaDB에서 검색할 retriever 생성
retriever = vectorstore.as_retriever(
    search_type="similarity"
    ,search_kwargs={"k": 5}
)

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

query = "기침과 발열이 있는 질병은 무엇인가요?"
response = qa_chain.invoke({"input": query})

print(" 질문: ", query)
print(" 답변: ", response["answer"])

