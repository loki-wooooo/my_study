'''
    langchain
    streamlit
    PyPDF2
    sentence-transformers
'''

import os
from PyPDF2 import PdfReader
import streamlit as st
from langchain.text_splitter import CharacterTextSplitter
from langchain.embeddings import HuggingFaceBgeEmbeddings
from langchain import FAISS
from langchain.chains.question_answering import load_qa_chain
from langchain.chat_models import ChatOpenAI
from langchain.callbacks import get_openai_callback

#CharacterTextSplitter를 사용하여 텍스트를 청크로 분할
def process_text(text):
    text_splitter = CharacterTextSplitter(
        separator="\n",
        chunk_size=1000,
        chunk_overlap=200,
        length_function=len # 텍스트 청크의 길이를 측정하는 방법
    )
    chunks = text_splitter.split_text(text)
    
    #임베딩 처리(백터 변환), 임베딩은 HuggingFaceEmbeddings 모델을 사용
    embeddings = HuggingFaceBgeEmbeddings(model_name='sentence-transformers/all-MiniLM-L6-V2')
    documents = FAISS.from_texts(chunks, embeddings)
    return documents

# streamlit을 이용한 웹사이트 구현
def main():
    st.title("PDF 요약하기")
    st.divider()
    try:
        os.environ["OPENAI_API_KEY"] = "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"
    except ValueError as e:
        st.error(str(e))
        return
    
    pdf = st.file_uploader('PDF파일을 업로드해주세요.', type='pdf')
    
    if pdf is not None:
        pdf_reader = PdfReader(pdf)
        text = '' #텍스트 변수에 PDF 내용을 저장
        for page in pdf_reader.pages:
            text += page.extract_text()
        
        documents = process_text(text)
        
        #LLM에 PDF 파일 요약 요청
        query = "업로드된 PDF 파일의 내용을 약 3~5문장으로 요약해주세요."
        
        if query:
            docs = documents.similarity_search(query)
            llm = ChatOpenAI(model='gpt-4o-mini', temperature=0.1)
            chain = load_qa_chain(llm, chain_type="stuff")
            
            with get_openai_callback() as cost:
                response = chain.run(input_documents=docs, question=query)
                print(cost)
            
            st.subheader('--- 요약 결과 ---')
            st.write(response)

if __name__ == '__main__':
    main()