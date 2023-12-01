import requests
import json

# 요청할 URL
url = 'http://localhost:8080/api/v1/users/register'

# 요청 본문에 들어갈 데이터
data = {
    'username': 'test1',
    'password': '1234',
    'name': 'seungwoo1',
    'phoneNumber': '01012341234',
    'gender': 'MALE',  # 또는 Gender.MALE에 해당하는 값으로 변경
    'birthday': '2023-11-30',  # 실제 데이터로 변경,
    'authenticationType': 'PASSWORD'
}

# POST 요청 보내기
response = requests.post(url, json=data)

# 응답 확인
print(response.status_code)
print(response)  # 만약 응답이 JSON 형식이라면 출력
