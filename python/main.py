from sqlalchemy import create_engine
import pymysql
import time
import serverInfo
import jusik_csv
import theme_csv
import news_csv
import top_jusik
import netPurchase
from indCodeName import getIndCodeName
from stocklist import getCodeName
from pywinauto import application
from datetime import datetime
import pandas as pd

if __name__ == "__main__":

    # try :
    #     # 대신 login
    #     app = application.Application()
    #     print("cybosplus 실행")
    #     app.start('C:\\DAISHIN\\STARTER\\ncStarter.exe /prj:cp /id:#### /pwd:#### /pwdcert:#### /autostart')
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
    CodeName = getCodeName()
    indCodeName = getIndCodeName()
    per = jusik_csv.jusik()
    theme = theme_csv.theme(dateString)
    forNetPurchase= netPurchase.netPurchase(1)
    orgNetPurchase = netPurchase.netPurchase(2)
    
    top_ten = top_jusik.get_topten_jusik()

    # print("외국인 상위 10", netPurchase.netPurchase(1))
    # print("기관계 상위 10", netPurchase.netPurchase(2))

    # data to db
    CodeName.to_sql(name= 'codename', con=db_connection, if_exists='replace',index=False)
    indCodeName.to_sql(name= 'indcodename', con=db_connection, if_exists='replace',index=False)
    per.to_sql(name=dateString + '_per', con=db_connection, if_exists='replace',index=False) #if_exists : append, replace, fail(dafault)
    theme.to_sql(name=dateString + '_theme', con=db_connection, if_exists='replace', index=False)
    orgNetPurchase.to_sql(name='orgdata', con=db_connection, if_exists='replace',index=False)
    forNetPurchase.to_sql(name='fordata', con=db_connection, if_exists='replace',index=False)
    top_ten.to_sql(name=f"{dateString}_top", con=db_connection, if_exists='replace', index=False)

    news = pd.DataFrame()

    for code in top_ten["stockCode"]:
        news = news_csv.news(code) if news.empty else news.append(news_csv.news(code))
        
    news.to_sql(name=dateString + "_news", con=db_connection, if_exists='replace', index=False)



    theme_stock_data = pd.DataFrame()

    for themeCode in theme.themeCode:
        theme_stock_data = theme_csv.getStockFromTheme(themeCode) if theme_stock_data.empty else theme_stock_data.append(theme_csv.getStockFromTheme(themeCode))
       
    theme_stock_data.to_sql(name=dateString + f"_stockintheme", con=db_connection, if_exists='replace', index=False)
