import os

# openai 키 입력, OpenAI API 키를 환경 변수(os.environ)에 저장
os.environ[
    "OPEN_API_KEY"] = "sk-proj-1234111lyzhcDArcsq14TTUOjtLT00Zvg3uS5BThuNDpEhmZ54eEelQXAzp22d3lemJZbv2iXMy-MoKTjT3BlbkFJt5HBN24vLXXO9qMhGxq5kqUFWiprtAlZDclS0VwHGHPzMuGbXZ1aSRkw7stwXWKs9JHBBN778A"

from langchain_openai import ChatOpenAI
llm = ChatOpenAI(
    model="gpt-4o",
    temperature= 0,
)


# 문서 로드에 대한 패키지 변경
# from langchain.document_loaders import PyPDFLoader
from langchain_community.document_loaders import PyPDFLoader
docs = []

# PDF 로드
loader = PyPDFLoader("C:\\utils\\05.work\\my_study\\backend\\03. python\\llm\\rag\\chapter05\\data\\안구건조증.pdf")

# 텍스트 데이터를 추출하여 리스트 형태로 반환
text_content = loader.load()

# 랭체인과 chromaDB를 활용하여 문서를 백터화, 검색할 수 있도록 백터 데이터 베이스 구축
from langchain_openai import OpenAIEmbeddings
# 백터 데이터를 저장하고 검색하는 벡터 데이터베이스(ChromaDB)
from langchain_chroma import Chroma
from langchain_text_splitters import CharacterTextSplitter

# 문서 분할(텍스트를 작은 단위로 나누기)
text_splitter = CharacterTextSplitter(
    separator="\n\n",
    chunk_size=100,
    chunk_overlap=10,
    length_function=len,
    is_separator_regex=False,
)

# 문서를 CharacterTextSplitter를 사용하여 분할
splits = text_splitter.split_documents(text_content)

# 분할된 문서에서 텍스트만 추출
texts = [doc.page_content for doc in splits]

# OpenAI 임베딩 모델 초기화
embeddings = OpenAIEmbeddings()

# 변환된 벡터를 Chroma 벡터 데이터베이스에 저장
vectorstore = Chroma(
    persist_directory='C:\\utils\\05.work\\my_study\\backend\\03. python\\llm\\rag\\chapter05\\data\\db_chroma_complete',
    embedding_function=embeddings
)

# 안구건조증 관련 정보에 특화된 프롬프트 설정하기


from langchain_core.prompts import PromptTemplate
custom_prompt_template = PromptTemplate(
    template="""당신은 안구건조증에 대한 정보를 제공하는 건강 지킴이 챗봇입니다.
    
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
chain_type_kwargs={"prompt": custom_prompt_template}

# ChromaDB를 활용하여 사용자의 질문(query)과 가장 유사한 문서를 검색
query = "습도가 안구건조증에 미치는 영향은?"

# vectorstore(ChromaDB 백터 저장소)에서 검색 수행
# k -> 질문과 유사도가 높은 청크값 리턴
retriever = vectorstore.similarity_search(query, k=8)

# 저장된 백터 데이터베이스에서 문서를 검색 GPT-4o와 결합하여 질의에 답변
from langchain.chains import RetrievalQA

# RetrievalQA.from_chain_type()을 사용하여 QA 체인 구성
qa_chain = RetrievalQA.from_chain_type(
    llm=llm,
    chain_type="stuff", # 기본적으로 retriever가 검색한 문서를 합쳐서 처리, context에 자동으로 검색결과를 대입하여 처리함
    retriever=vectorstore.as_retriever(),
    chain_type_kwargs=chain_type_kwargs
)

# 사용자의 질문을 입력하여 검색 및 응답 생성
result = qa_chain.invoke({"query": query})
result["result"]



### 이전의 대화이력에 대한 결과값을보고 추가질문시 사용처리
# ConversationBufferMemory -> 챗봇이 대화이력을 저장하고 관리하도록 설정
# 랭체인에서 제공하는 메모리 관리 클래스중 하나, 대화 내역을 저장하고 유지하는 역할을 함

from langchain.memory import ConversationBufferMemory

memory = ConversationBufferMemory(
    # 저장된 대화를 "chat_history"라는 키 값으로 관리
    memory_key="chat_history",
    # AI 가 대화 문맥을 이해할 수 있게 이전 대화를 메시지 형식(List[Message])으로 반환
    return_messages=True, # HumanMessage, AIMessage 같은 메시지 객체 목록으로 반환
    output_key="output" # 출력 데이터를 저장할 키 값
)

# 문맥을 유지하면서 검색 기능을 추가한 질의응답(QA) 체인 라이브러리
from langchain.chains import ConversationalRetrievalChain
retriever = vectorstore.as_retriever()
qa_chain_memory = ConversationalRetrievalChain.from_llm(
    llm,
    # ChromaDB의 벡터 데이터베이스에서 유사한 문서를 검색할 수 있도록 설정
    retriever=retriever,
    # 이전 대화를 기억하여 문맥을 유지할 수 있도록 설정
    memory=memory,
)

query="안구건조증 예방 방법은?"
result=qa_chain.invoke({"query":query})
result["result"]
