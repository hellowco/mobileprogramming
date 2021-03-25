#########################
# 업종명 가져오는 코드

import pandas as pd
import win32com.client

objCpCodeMgr = win32com.client.Dispatch("CpUtil.CpCodeMgr")

data = pd.DataFrame(columns=['indCode', 'indName'])

for i in range(0,1000):
    name = objCpCodeMgr.GetIndustryName(i)
    code = i;
    data = data.append({'indCode': code,'indName': name}, ignore_index=True)

data.to_csv('indCodeName.csv',encoding='utf-8')