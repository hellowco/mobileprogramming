import pandas as pd
import win32com.client

def news(stockCode, themeCode):
    '''
    종목 코드로 해당 종목에 관한 특징 뉴스 저장.
    '''
    objCpMarketWatch = win32com.client.Dispatch("CpSysDib.CpMarketWatch")
    objCpMarketWatch.SetInputValue(0, stockCode)
    rqField = '1,2,10,11,12,13' 
    objCpMarketWatch.SetInputValue(1, rqField)
    objCpMarketWatch.BlockRequest()
    cnt = objCpMarketWatch.GetHeaderValue(2) 
    cnt = 3 if cnt >= 4 else cnt
    
    data = pd.DataFrame(columns=["stockCode", "themeCode", "stockName", "time", "stockNews"])

    for k in range(cnt):
        stockCode = objCpMarketWatch.GetDataValue(1, k)
        stockName = objCpMarketWatch.GetDataValue(2, k)
        time = "%2d:%2d" %divmod(objCpMarketWatch.GetDataValue(0, k), 100) 
        stockNews = objCpMarketWatch.GetDataValue(4, k)

        data = data.append({"stockCode":stockCode,
                            "themeCode":themeCode,
                            "stockName":stockName,
                            "time"     :time,
                            "stockNews":stockNews}, ignore_index=True)
        
    return data
