import os


# openai 키 입력, OpenAI API 키를 환경 변수(os.environ)에 저장
os.environ[
    "OPEN_API_KEY"] = "sk-proj-1234111lyzhcDArcsq14TTUOjtLT00Zvg3uS5BThuNDpEhmZ54eEelQXAzp22d3lemJZbv2iXMy-MoKTjT3BlbkFJt5HBN24vLXXO9qMhGxq5kqUFWiprtAlZDclS0VwHGHPzMuGbXZ1aSRkw7stwXWKs9JHBBN778A"
os.environ["USER_AGENT"] = "MyLangChainApp/1.0"

from langchain_openai import ChatOpenAI, OpenAIEmbeddings

llm = ChatOpenAI(model_name="gpt-4o", )

from langchain.document_loaders import WebBaseLoader # 웹을 크롤링 후 텍스트를 추출하는 크롤러
from langchain.vectorstores import Chroma # 백터DB
from langchain.text_splitter import RecursiveCharacterTextSplitter # 텍스트를 작은 청크로 분할
from langchain.chains import RetrievalQA

# 웹 크롤링하여 텍스트 데이터 갖고오기
# AI 관련 위키 문서
url = "https://ko.wikipedia.org/wiki/인공지능"

'''
print(type(loader))                 # <class '...WebBaseLoader'> (버전에 따라 경로 달라질 수 있음)
print(loader.web_paths)             # ['https://ko.wikipedia.org/wiki/인공지능']

docs = loader.load()
print(type(docs), len(docs))        # <class 'list'> 1
print(type(docs[0]).__name__)       # 'Document'
print(docs[0].metadata.get('source'))   # https://ko.wikipedia.org/wiki/인공지능
print(docs[0].metadata.get('title'))    # 인공지능 — 위키백과 (있다면)
print(docs[0].metadata.get('language')) # ko (있다면)
print(docs[0].page_content[:200])       # 본문 텍스트 앞부분
print(len(docs[0].page_content))        # 본문 전체 길이(대략 수만~수십만 자 가능)
'''

# 웹 페이지의 내용을 갖고와 랭체인 문서 객체로 변환
loader = WebBaseLoader(web_path=url)
# 웹 페이지에서 텍스트 데이터 추출
docs = loader.load()

# 텍스트를 작은 단위로 분할
# 웹에서 가져온 문서(document)를 500자 단위로 분할
# 은 텍스트를 여러 조각으로 나눌 때, 인접한 조각들 사이에 겹쳐서 포함할 문자(혹은 토큰) 수를 지정하는 속성 `chunk_overlap`
text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=50)
split_docs = text_splitter.split_documents(docs)

# OpenAI 임베딩 모델 로드
embeddings = OpenAIEmbeddings()

# ChromaDB 벡터 저장소 생성 및 데이터 저장
# 분할된 문서를 벡터화하여 ChromaDB에 저장, 재사용 가능하도록 로컬 디스크에 저장
db = Chroma.from_documents(
    split_docs
    , embeddings
    , persist_directory="./chroma_db"
)

# ChromaDB에서 검색할 retriever 생성
# 유사한 문서를 검색, 최대 5개의 유사한 검색 결과 반환
retriever = db.as_retriever(
    search_type="similarity"
    , search_kwargs={"k": 5}
)

# LangChain Q&A 체인 설정
# 검색된 문서를 기반으로 LLM이 답변 생성
'''
chain_type: 문서를 LLM에 넣는 방식
    stuff(가볍고 빠름), map_reduce(대량 문서), refine(점진적 보강), map_rerank(재랭킹 기반)

'''
qa_chain = RetrievalQA.from_chain_type(
    llm=llm
    , retriever=retriever
    , chain_type="stuff"
)

# 사용자 질문 입력, 결과를 반환을 위한 검색 및 답변 생성
# invoke => 입력을 주면 그 체인이 정의한 파이프라인(예: 검색 → 프롬프트 구성 → LLM 호출)을 한 번 돌리고 결과를 반환
query = 'RAG란?'
response = qa_chain.invoke(query)

print('답변: ')
print(response)