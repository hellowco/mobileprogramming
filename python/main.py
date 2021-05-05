from sqlalchemy import create_engine
import pymysql
import time
import serverInfo
import jusik_csv
import theme_csv
import news_csv
import netPurchase
from indCodeName import getCodeName
from pywinauto import application
from datetime import datetime


if __name__ == "__main__":

    # try :
    #     # 대신 login
    #     app = application.Application()
    #     print("cybosplus 실행")
    #     app.start('C:\\DAISHIN\\STARTER\\ncStarter.exe /prj:cp /id:isungun1 /pwd:aptes#1 /pwdcert:doqxptmxm#1 /autostart')
    #     time.sleep(30)
    # except: 
    #     print('증권 api 로그인 실패')
    try: 
        #DB login
        db_connection_str = 'mysql+pymysql://'+serverInfo.user+':'+serverInfo.password+'@'+serverInfo.host+'/'+serverInfo.dbname
        db_connection = create_engine(db_connection_str)
        conn = db_connection.connect()
    except:
        print('db connection failed')

    dateString = datetime.strftime(datetime.now(), '%Y%m%d')

    # get api data
    codeName = getCodeName()
    per = jusik_csv.jusik(dateString)
    orgNetPurchase= netPurchase.netPurchase(1)
    forNetPurchase = netPurchase.netPurchase(2)

    theme = theme_csv.theme(dateString)

    
    #Debug
    # for themeCode in theme.themeCode:
    #     stockData = theme_csv.getStockFromTheme(themeCode).stockCode
    #     for stockCode in stockData:
    #         news_list.append(news_csv.news(stockCode, themeCode))

    # print("외국인 상위 10", netPurchase.netPurchase(1))
    # print("기관계 상위 10", netPurchase.netPurchase(2))

    # data to db
    codeName.to_sql(name= 'CodeName', con=db_connection, if_exists='replace',index=False)
    per.to_sql(name=dateString + '_PER', con=db_connection, if_exists='replace',index=False) #if_exists : append, replace, fail(dafault)
    theme.to_sql(name=dateString + '_Theme', con=db_connection, if_exists='replace', index=False)
    orgNetPurchase.to_sql(name='orgData', con=db_connection, if_exists='replace',index=False)
    forNetPurchase.to_sql(name='forData', con=db_connection, if_exists='replace',index=False)

    for themeCode in theme.themeCode:
        theme_stock_data = theme_csv.getStockFromTheme(themeCode)
        theme_stock_data.to_sql(name=dateString + f"_{themeCode}", con=db_connection, if_exists='replace', index=False)
        for stock in theme_stock_data.stockCode:
                news_csv.news(stock, themeCode).to_sql(name=dateString + f"_{stock}_News", con=db_connection, if_exists='replace', index=False)

    #
    # userContainer = dict() or list()
    # for k in range(numofusers):
    #   user = User(username)
    #   userContainer[username] = user
    #   

    # while (true):
    #   if user event on:
    #       eventQueue.enqueue(userEvent)

    # For every eventQueue:
    # userContainer[eventUsername].setInterest()
    # userContainer[eventUsername].showRelated()
    # userContainer[eventUsername].intersted.to_sql(name=username+"_Interested", if_exist='replace')
    # userContainer[eveneUsername].related.to_sql(name=username + "_Related", if_exist='replace')

