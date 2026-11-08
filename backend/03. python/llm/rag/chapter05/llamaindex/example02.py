import pandas as pd
from llama_index.core import VectorStoreIndex, Document
from llama_index.llms.openai import OpenAI
from llama_index.embeddings.openai import OpenAIEmbedding
from llama_index.core.settings import Settings


# VectorStoreIndex - 문서를 벡터화하여 저장하고 검색할 수 있도록 하는 벡터 저장소 인덱스
# Document - 텍스트 데이터를 라마인덱스에서 처리할 수 있는 문서 객체로 변환


# CSV 파일 경로 지정
file_path= 'C:\\utils\\05.work\\my_study\\backend\\03. python\\llm\\rag\\chapter05\\data\\files\\Disease_symptom_and_patient_profile_dataset.csv'
df = pd.read_csv(file_path)

# 각 행(Row)에 대해 정보를 하나의 문자열로 합쳐서 저장
df["text"] = df.apply(
    lambda row: 
        f"질병: {row['Disease']}, 증상: 발열 ({row['Fever']}), 기침 ({row['Cough']}), 피로 ({row['Fatigue']}), 호흡곤란 ({row['DifficultyBreathing']}),"
        f"연령: {row['Age']}, 성별: {row['Gender']}, 혈압: {row['Blood Pressure']}, 콜레스테롤: {row['Cholesterol Level']}, 결과: {row['Outcome Variable']}", axis=1
)

# OpenAI LLM 및 임베딩 모델 설정
Settings.llm = OpenAI(model='gpt-4o', temperature=0)
Settings.embed_model = OpenAIEmbedding()

# CSV 데이터를 Document 객체로 변환
documents = [Document(text=text) for text in df['text'].tolist()]
# 문서를 벡터화하여 LlamaIndex 벡터 저장소에 저장
index = VectorStoreIndex.from_documents(documents)

# 검색 기능을 수행하는 query_engine 생성
query_engine = index.as_query_engine()

# 사용자 질문
query = "기침과 발열이 있는 질병은 무엇인가요?"
response = query_engine.query(query)

# 결과 출력
print(f"질문 : {query}")
print(f"답변 : {response}")