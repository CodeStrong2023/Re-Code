from psycopg2 import pool
from person_data_layer.logger_base import log
import sys


class Connection:
    _DATABASE = "users_test"
    _USERNAME = "postgres"
    _PASSWORD = "root"
    _DB_PORT = "5432"
    _HOST = "127.0.0.1"
    _MIN_CON = 1
    _MAX_CON = 5
    _pool = None

    @classmethod
    def get_connection(cls):
        connection = cls.get_pool().getconn()
        log.debug(f"Connection obtained from the pool: {connection}")
        return connection

    @classmethod
    def get_pool(cls):
        if cls._pool is None:
            try:
                cls._pool = pool.SimpleConnectionPool(cls._MIN_CON,
                                                      cls._MAX_CON,
                                                      host=cls._HOST,
                                                      user=cls._USERNAME,
                                                      password=cls._PASSWORD,
                                                      port=cls._DB_PORT,
                                                      database=cls._DATABASE)
                log.debug(f"Successful pool connection: {cls._pool}")
                return cls._pool
            except Exception as e:
                log.error(f"An error occurred while getting the pool: {e}")
                sys.exit()
        else:
            return cls._pool

    @classmethod
    def release_connection(cls, connection):
        cls.get_pool().putconn(connection)
        log.debug(f"Returned the connection to the pool: {connection}")

    @classmethod
    def close_connections(cls):
        cls.get_pool().closeall()