import requests
    
app_name = "timePay"

base_url = f"http://localhost:8080/api/v1/payapp/register/{app_name}"

# GET 요청 보내기
response = requests.get(base_url)

print(response.status_code)
print(response.text)