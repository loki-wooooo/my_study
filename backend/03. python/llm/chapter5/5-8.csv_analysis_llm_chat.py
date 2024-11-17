'''
    라이브러리 : Langchain, openai, streamlit
    언어모델 : gpt-3.5-turbo-16k-0613 -> "gpt-4o-mini" 변경처리
    임베딩 모델 : -
'''

'''
LangChain에서 사용할 수 있는 Agent 목록
Pandas DataFrame Agent:
    데이터 분석과 조작을 위해 Pandas DataFrame과 상호작용합니다.
    예: create_pandas_dataframe_agent()를 사용하여 데이터프레임 분석 수행
Wikipedia Agent:
    Wikipedia API를 통해 정보를 검색하고 요약합니다.
    예: WikipediaQueryRun(api_wrapper=WikipediaAPIWrapper())
OpenAI Functions Agent:
    OpenAI의 함수 호출 기능을 활용하는 agent입니다.
    예: AgentType.OPENAI_FUNCTIONS를 사용하여 agent 생성
Zero-Shot React Description Agent:
    사전 정의된 도구 없이 작업을 수행할 수 있는 유연한 agent입니다.
    예: agent_type="zero-shot-react-description"
Structured Chat Agent:
    구조화된 대화를 위한 agent입니다.
ReAct Agent:
    추론과 행동을 결합한 agent입니다.
Self-Ask Agent:
    복잡한 질문을 하위 질문으로 나누어 처리하는 agent입니다.
XML Agent:
    XML 형식의 출력을 생성하는 agent입니다.
JSON Chat Agent:
    JSON 형식의 출력을 생성하는 agent입니다.
Custom Tools Agent:
    사용자 정의 도구를 통합한 agent를 만들 수 있습니다.

'''

# 파이썬 언어로 작성된 데이터를 분석 및 조작하기 위한 라이브러리
import pandas as pd
# csv 파일을 데이터프레임으로 가저오기
df = pd.read_csv("/Users/administration/Downloads/git/my_study/backend/03. python/llm/chapter5/data/booksv_02.csv")#booksv_02.csv 파일이 있는 경로 지정!
df.head()

from langchain_experimetal.agents.agent_toolkits import create_pandas_dataframe_agent
from langchain.chat_models import ChatOpenAI
from langchain.agents.agent_types import AgentType
import os 
os.environ["OPENAI_API_KEY"] = "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"

#에이전트 생성
agent = create_pandas_dataframe_agent(
    ChatOpenAI(temperature=0, model='gpt-4o-mini'), # gpt 사용
    df, # 데이터가 실제 담긴 곳
    verbose=False, #추론 과정을 출력하지 않음
    agent_type=AgentType.OPENAI_FUNCTIONS,
)
# 기대 답변 -> '제품 중에서 ratings_count가 가장 높은 책은 "One Hundred Years of Solitude" 입니다.'
agent.run('어떤 제품의 ratings_count가 제일 높아?')

# 기대 답변 -> '가장 최근에 출간된 책은 "The Yiddish Policemen's Union" 입니다.'
agent.run('가장 최근에 출간된 책은?')