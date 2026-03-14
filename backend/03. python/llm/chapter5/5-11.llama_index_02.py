# 변경 후 - 랭체인 X 라마인덱스 사용

import os

os.environ[
    "OPENAI_API_KEY"] = "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"

import  pandas as pd
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(
    model_name="gpt-4o",
    temperature=0,
)

from llama_index.core import VectorStoreIndex, Document, Settings
from llama_index.llms.openai import OpenAI
from llama_index.embeddings.openai import OpenAIEmbedding

# 데이터 불러오기
file_path = "C:\\utils\\03.work\\01.my\\my_study\\backend\\03. python\\llm\\chapter5\\data\\Disease_symptom_and_patient_profile_dataset.CSV"
df = pd.read_csv(file_path)

# 데이터를 텍스트로 변환
df["text"] = df.apply(
    lambda row: f"질병: {row['Disease']}, 증상: {row['Symptom']}, 환자 프로필: {row['Patient Profile']}", axis=1
)


# openAi LLM 및 임베딩 모델 설정
Settings.llm = OpenAI(model="gpt-4o", temperature=0)
Settings.embed_model = OpenAIEmbedding()

# CSV 데이터를 Document 객체로 변환
document = [Document(text=text) for text in df["text"].tolist()]
# 문서를 벡터화하여 LlamaIndex 벡터 저장소에 저장
index = VectorStoreIndex.from_documents(document)

# 검색 기능을 수행하는 query_engine 생성
query_engine = index.as_query_engine()

# 사용자가 입력한 질문을 기반으로 질의응답 수행
query = "기침과 발열이 있는 질병은 무엇인가요?"
response = query_engine.query(query)

print(" 질문: ", query)
print(" 답변: ", response["answer"])
