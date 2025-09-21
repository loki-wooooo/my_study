import os

os.environ[
    "OPENAI_API_KEY"] = "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"

from langchain_openai import ChatOpenAI

llm = ChatOpenAI(
    model_name="gpt-4o-mini"
    , temperature=0
)

from langchain.document_loaders import PyPDFLoader

docs = []  # 추출된 문서를 저장할 리스트를 초기화

# PYPDFLoader 를 이용해서 PDF 로드
loader = PyPDFLoader("C:\\utils\\03.work\\01.my\\my_study\\backend\\03. python\\llm\\chapter5\\data\\안구건조증.pdf")

# PDF에서 텍스트 데이터를 추출하여 리스트 형태로 반환
text_content = loader.load()

from langchain_openai import OpenAIEmbeddings
# 벡터 데이터를 저장하고 검색하는 벡터 데이터베이스(ChromaDB)
from langchain_chroma import Chroma
from langchain.text_splitter import CharacterTextSplitter

# 문서 분할(텍스트를 작은 단위로 나누기)
text_splitter = CharacterTextSplitter(
    separator="\n\n",
    chunk_size=1000,
    chunk_overlap=10,
    length_function=len,  # 청크의 “길이”를 어떻게 계산할지 정하는 콜백
    is_separator_regex=False
)

# 문서를 CharacterTextSplitter를 사용하여 분할
splits = text_splitter.split_documents(text_content)

# 분할된 문서에서 텍스트만 추출
texts = [doc.page_content for doc in splits]

# OpenAI 임베딩 모델 초기화
embeddings = OpenAIEmbeddings()

# 변환된 벡터를 Chroma 벡터 데이터베이스에 저장
vectorstore = Chroma(
    persist_directory="C:\\utils\\03.work\\01.my\\my_study\\backend\\03. python\\llm\\chapter5\\data\\db_chroma_complete",
    embedding_function=embeddings
)

# 안구건조증 관련 정보에 특화된 프롬프트 설정
from langchain_core.prompts import PromptTemplate

custom_prompt_template = PromptTemplate(
    template="""당신은 안구건조증에 대한 정보를 제공하는 건강 지킴이 챗봇입니다.
    
    {context}
    
    제공된 소스 데이터만 사용하여 고객의 질문에 답하세요.
    모르겠다면 "잘 모르겠습니다."라고 답하세요
    친절하고 예의 바르며 전문적인 어조를 사용하세요.
    답변은 간결하게 유지하세요.
    
    질문:{question}
    
    답변: """,
    input_variables=["context"]
)
chain_type_kwargs = {"prompt": custom_prompt_template}

query = "습도가 인구건조증에 미치는 영향은?"

# vectorstore(ChromaDB 벡터 저장소)에서 검색 수행
# k=8 유사한 값 8개 표출
retriever = vectorstore.similarity_search(query, k=8)

from langchain.chains import RetrievalQA

# RetrievalQA.from_chain_type()을 사용하여 QA 체인 구성
qa_chain = RetrievalQA.from_chain_type(
    llm=llm,
    chain_type="stuff", # 기본적으로 Retrieval가 검색한 문서를 합쳐서 처리
    retriever=vectorstore.as_retriever(),
    chain_type_kwargs=chain_type_kwargs
)

result = qa_chain.invoke({"query": query})
result["result"]

# 랭체인에서 대화 이력을 저장하고 관리하는 라이브러리
from langchain.memory import ConversationBufferMemory

memory = ConversationBufferMemory(
    # 저장된 대화를 "chat_history"라는 키 값으로 관리
    memory_key="chat_history",
    # AI가 대화 문맥을 이해할 수 있게 이전 대화를 메시지 형식(List[Message])으로 반환
    return_messages=True,
    output_key="output" # 출력 데이터를 저장할 키 값
)

# 문맥을 유지하면서 검색 기능을 추가한 질의응답(QA) 체인 라이브러리
from