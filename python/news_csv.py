import pandas as pd
import win32com.client

def news(stockCode):
    '''
    종목 코드로 해당 종목에 관한 특징 뉴스 저장.
    '''
    print(stockCode) # 종목코드 출력
    objCpMarketWatch = win32com.client.Dispatch("CpSysDib.CpMarketWatch")
    objCpMarketWatch.SetInputValue(0, stockCode)
    rqField = '1,2,10,11,12,13' 
    objCpMarketWatch.SetInputValue(1, rqField)
    objCpMarketWatch.BlockRequest()
    cnt = objCpMarketWatch.GetHeaderValue(2) 
    cnt = 3 if cnt >= 4 else cnt
    
    data = pd.DataFrame(columns=["stockCode", "stockName", "time", "stockNews"])

    for k in range(cnt):
        stockCode = objCpMarketWatch.GetDataValue(1, k)
        stockName = objCpMarketWatch.GetDataValue(2, k)
        time = "%2d:%2d" %divmod(objCpMarketWatch.GetDataValue(0, k), 100) 
        stockNews = objCpMarketWatch.GetDataValue(4, k)

        data = data.append({"stockCode":stockCode,
                            "stockName":stockName,
                            "time":time,
                            "stockNews":stockNews}, ignore_index=True)
        
    # print(data)
    return data

# if __name__=="__main__":
#     news("A005930")