import pandas as pd
import win32com.client
import theme_csv
import pymysql

from datetime import datetime

class User:
    def __init__(self, username):
        self.username = username
        self.interested = None
        self.related = None

    def setUserInterest(self, stockCode, themeCode, conn):
        #Need db schema
        #Need username in userinfo table. 
        if not conn:
            print("db not connected")
            return

        data = pd.DataFrame(columns=["stockCode", "themeCode"])

        data = data.append(
            {
                "stockCode" : stockCode,
                "themeCode" : themeCode
            }, ignore_index=True)

        self.interested = data

        return data

    def selectRelatedInterest(self, stockCodes, date, conn):
        if conn:
            data = pd.DataFrame(columns=["RelatedStock"])

            for stockCode in stockCodes:
                themes = theme_csv.getThemeFromStock(stockCode)
            
                for theme in themes:
                    temp_data = pd.read_sql_query(f"SELECT stockCode FROM {date}_{theme} Where stockCode != {stockCode}", conn)

                    for related in temp_data:
                        data.append({
                            "RelatedStock" : related
                        }, ignore_index=True)

            self.related = data
            return data
        
        print("db not connected")
        return None
