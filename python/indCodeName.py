#########################
# 업종명 가져오는 코드

import pandas as pd
import win32com.client

def getIndCodeName():

    objCpCodeMgr = win32com.client.Dispatch("CpUtil.CpCodeMgr")
    data = pd.DataFrame(columns=['indCode', 'indName'])

    for i in range(1000):
        name = objCpCodeMgr.GetIndustryName(i)
        code = i;
        data = data.append({'indCode': code,'indName': name}, ignore_index=True)
        
    return data