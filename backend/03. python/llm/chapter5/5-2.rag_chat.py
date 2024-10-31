'''
    unstructured - 텍스트 파일 같은 구조화되지 않은 데이터를 다루는데 사용
    sentence-transformers - 문장을 백터로 변환
    chromadb - 백터를 저장하고 유사도 검색 지원
    opoenai - open ai sdk
'''

# textfile 호출
from langchain.document_loaders import TextLoader
path = "C:\\Utils\\04.study\\my_study\\backend\\03. python\\llm\\chapter5\\data\\AI.txt"
documents = TextLoader(path).load()

# textfile 청크로 분할
from langchain.text_splitter import RecursiveCharacterTextSplitter

# 문서를 청크로 분할
def split_docs(documents, chunk_size=1000, chunk_overlap=20):
    text_splitter = RecursiveCharacterTextSplitter(chunk_size=chunk_size, chunk_overlap=chunk_overlap)
    docs = text_splitter.split_documents(documents)
    return docs

# docs 변수에 분할 문서를 저장
docs = split_docs(documents)

# embedding 처리
from langchain.embeddings import SentenceTransformerEmbeddings
embeddings = SentenceTransformerEmbeddings(model_name='all-MiniLM-L6-v2')

# 백터 저장
from langchain.vectorstores import Chroma
db = Chroma.from_documents(docs, embeddings)

# openai 키 입력
import os
os.environ["OPENAI_API_KEY"] = "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"

from langchain.chat_models import ChatOpenAI
model_name = 'gpt-4o-mini'
llm = ChatOpenAI(model_name=model_name)

#Q&A 체인을 사용하여 쿼리에 대한 답변 얻기
'''
    chain_type -> 체인의 유형을 "stuff"로 지정합니다. 이는 모든 문서를 하나의 컨텍스트로 결합하는 방식
        map_reduce:
            긴 문서를 작은 청크로 나누어 처리합니다.
            각 청크에 대해 개별적으로 요약을 생성한 후, 이를 결합하여 최종 답변을 만듭니다.
            큰 문서나 많은 양의 텍스트를 처리할 때 유용합니다.
        refine:
            문서를 순차적으로 처리하며, 이전 답변을 점진적으로 개선합니다.
            각 단계에서 새로운 정보를 추가하거나 수정하여 답변의 질을 향상시킵니다.
        map_rerank:
            여러 문서 청크에 대해 답변을 생성한 후, 가장 관련성 높은 답변을 선택합니다.
            점수화 시스템을 사용하여 최적의 답변을 선별합니다.
        SimpleSequentialChain:
            여러 체인을 순차적으로 연결합니다.
            각 단계의 출력이 다음 단계의 입력으로 사용됩니다.
        SequentialChain:
            SimpleSequentialChain보다 더 복잡한 형태로, 여러 입력과 출력을 처리할 수 있습니다.
        RouterChain:
            입력에 따라 다른 체인으로 라우팅할 수 있는 조건부 로직을 제공합니다.
        TransformChain:
            체인 사이에서 데이터 변환을 수행합니다.
    verbose -> 상세한 출력을 활성화
'''
from langchain.chains.question_answering import load_qa_chain
chain = load_qa_chain(llm, chain_type="stuff", verbose=True)

#쿼리를 작성하고 유사도 검색을 수행하여 답변을 생성, 따라서 텍스트에 있는 내용을 질의
query = "AI란?"
matching_docs = db.similarity_search(query)
answer = chain.run(input_documents=matching_docs, question=query)