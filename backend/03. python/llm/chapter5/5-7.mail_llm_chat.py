'''
    라이브러리 : Langchain, openai, streamlit
    언어모델 : gpt-4o-mini
    임베딩 모델 : -
'''

import streamlit as st
import os
os.environ["OPENAI_API_KEY"] = "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"

st.set_page_config(page_title="이메일 작성 서비스", page_icon=":rotot:")
st.header("이메일 작성기")

def getEmail():
    input_text = st.text_area(label="메일 입력", label_visibility='collapsed', placeholder='당신의 메일은...', key=input_text)
    return input_text

input_text = getEmail()

#이메일 변환 작업을 위한 템플릿 정리
query_template="""
    메일을 작성해주세요.
    아래는 이메일입니다:
    이메일: {email}
"""

#PromptTemplate 인스턴스 생성
from langchain import PromptTemplate
#PromptTemplate 인스턴스 생성
prompt = PromptTemplate(
    input_variable=['email'],
    template=query_template
)

#GPT-4o-mini 설정
from langchain.llms import OpenAI
#언어 모델 호출
def looadLanguageModel():
    llm = OpenAI(model_name="gpt-4o-mini", temperature=.7)
    return llm

#예시 이메일 표시
st.button("*예제를 보여주세요*", type='secondary', help='봇이 작성한 메일을 확인해보세요')
st.markdown("### 봇이 작성한 메일은:")

if input_text:
    llm = looadLanguageModel()
    #PromptTemplate 및 언어 모델을 사용하여 이메일 형식 지정
    prompt_with_email = prompt.format(email=input_text)
    formatted_email = llm(prompt_with_email)
    #서식이 지정된 이메일 표시
    st.write(formatted_email)