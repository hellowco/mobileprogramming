from sqlalchemy import create_engine
import pymysql
import time
import serverInfo
import jusik_csv
import theme_csv
import news_csv
import netPurchase
from indCodeName import getIndCodeName
from stocklist import getCodeName
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
    # CodeName = getCodeName()
    # indCodeName = getIndCodeName()
    # per = jusik_csv.jusik(dateString)
    # theme = theme_csv.theme(dateString)
    forNetPurchase= netPurchase.netPurchase(1)
    orgNetPurchase = netPurchase.netPurchase(2)

    #Debug
    # for themeCode in theme.themeCode:
    #     stockData = theme_csv.getStockFromTheme(themeCode).stockCode
    #     for k in stockData:
    #         news_csv.news(k)
            

    
    # print("외국인 상위 10", netPurchase.netPurchase(1))
    # print("기관계 상위 10", netPurchase.netPurchase(2))

    # data to db
    # CodeName.to_sql(name= 'codename', con=db_connection, if_exists='replace',index=False)
    # indCodeName.to_sql(name= 'indcodeName', con=db_connection, if_exists='replace',index=False)
    # per.to_sql(name=dateString + '_PER', con=db_connection, if_exists='replace',index=False) #if_exists : append, replace, fail(dafault)
    # theme.to_sql(name=dateString + '_theme', con=db_connection, if_exists='replace', index=False)
    orgNetPurchase.to_sql(name='orgdata', con=db_connection, if_exists='replace',index=False)
    forNetPurchase.to_sql(name='fordata', con=db_connection, if_exists='replace',index=False)

    # for k in theme.themeCode:
    #     theme_csv.getStockFromTheme(k).to_sql(name=dateString + '_themestocks', con=db_connection, if_exists='append', index=False)
    #     for k in stockData:
    #             news_csv.news(k).to_sql(name=dateString + '_stocknews', con=db_connection, if_exists='replace', index=False)
