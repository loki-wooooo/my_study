'''
    라이브러리: langchain, streamlit, PyPDF2
    언어 모델 : gpt-4o-mini
    임베딩 모델 : all-MiniLM-L6-v2
    백터 데이터베이스 : 파이스(FAISS)
'''

import streamlit as st
from PyPDF2 import PdfReader
'''
    SentenceTransformerEmbeddings: 오픈 소스 프로젝트로, 다양한 사전 훈련된 모델을 제공합니다.
    OpenAIEmbeddings: OpenAI에서 개발한 상용 서비스입니다.
'''
from langchain.embeddings import OpenAIEmbeddings, SentenceTransformerEmbeddings
from langchain.chat_models import ChatOpenAI
from langchain.chains import ConversationalRetrievalChain, RetrievalQA
from langchain.memory import ConversationBufferWindowMemory
from langchain.vectorstores import FAISS
from langchain.document_loaders import PyPDFLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter

#PDF 문서에서 텍스트를 추출
def get_pdf_text(pdf_docs):
    text = ""
    for pdf in pdf_docs:
        pdf_reader = PdfReader(pdf)
        for page in pdf_reader.pages:
            text += page.extract_text()
    return text

#지정된 조건에 따라 주어진 텍스트를 더 작은 덩어리로 분할
def get_text_chunks(text):
    '''
        chunk_overlap은 텍스트 분할(chunking) 과정에서 사용되는 중요한 개념입니다. 주요 특징은 다음과 같습니다:
    '''    
    text_splitter = RecursiveCharacterTextSplitter(
        separators="\\n",
        chunk_size=1000,
        chunk_overlap=200,
        length_function=len
    )
    chunks = text_splitter.split_text(text)
    return chunks

#주어진 텍스트 청크에 대한 임베딩을 생성하고 파이스(FAISS)를 사용하여 백터 저장소를 생성
def get_vectorstore(text_chunks):
    embeddings = SentenceTransformerEmbeddings(model_name='all-MiniLM-L6-v2')
    vectorstore = FAISS.from_text(texts=text_chunks, embeddings=embeddings)
    return vectorstore


import os
os.environ["OPENAI_API_KEY"] = "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"

#주어진 백터 저장소로 대화 체인을 초기화
def get_conversation_chain(vectorstore):
    # ConversationBufferWindowMemory - 주고 받은 대화를 저장
    memory = ConversationBufferWindowMemory(memory_key='chat_history', return_message=True)
    
    # ConversationalRetrievalChain을 통해 랭체인 챗봇에 쿼리 전송
    conversation_chain = ConversationalRetrievalChain.from_llm(
        llm=ChatOpenAI(temperature=0, model_name='gpt-4o-mini'),
        retriever=vectorstore.as_retriever(),
        get_chat_history=lambda h:h,
        memory=memory
    )
    return conversation_chain

user_uploads = st.file_uploader("파일을 업로드 해주세요", accept_multiple_files=True)
if user_uploads is not None:
    if st.button("Upload"):
        with st.spinner("처리중.."):
            #PDF 텍스트 가저오기
            raw_text = get_pdf_text(user_uploads)
            #텍스트에서 청크 검색
            text_chunks = get_text_chunks(raw_text)
            #PDF 텍스트 저장을 위해 파이스(FAISS) 백터 저장소 만들기
            vectorstore = get_vectorstore(text_chunks)
            #대화 체인 만들기
            st.session_state.conversation = get_conversation_chain(vectorstore)

if user_query := st.chat_input("질문을 입력해 주세요"):
    #대화 체인을 사용하여 사용자의 메시지를 처리
    if 'conversation' in st.session_state:
        result = st.session_state.conversation({
            "question": user_query, 
            "chat_history": st.session_state.get('chat_history', [])
        })
        response = result['answer']
    else:
        response = '먼저 문서를 업로드 해주세요.'
        
    with st.chat_message("assistant"):
        st.write(response)