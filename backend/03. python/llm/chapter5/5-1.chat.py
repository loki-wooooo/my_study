'''
    streamlit
    langchain
    openai
'''

import streamlit as st
from langchain.llms import OpenAI
st.set_page_config(page_title=" 뭐든지 질문하세요~ ")
st.title("뭐든지 질문하세요")

# openai 키 입력
import os
os.environ["OPENAI_API_KEY"] = "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"

# llm 답변 생성
def generate_response(input_text):
    '''
        temperature - 정확한 답변 제공 (0~1 사이)
    '''
    llm = OpenAI(model_name='gpt-4o-mini', temperature=0)
    st.info(llm(input_text))
    
with st.form('Question'):
    text = st.text_area('질문 입력:', 'What types of text models does OpenAI provide?')
    submitted = st.form_submit_button('보내기')
    generate_response(text)