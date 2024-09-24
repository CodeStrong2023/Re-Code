from person_data_layer.logger_base import log
from person_data_layer.connection import Connection


class CursorFromPool:
    def __init__(self):
        self._connection = None
        self._cursor = None

    def __enter__(self):
        log.debug('Start of with method and __enter__')
        self._connection = Connection.get_connection()
        self._cursor = self._connection.cursor()
        return self._cursor

    def __exit__(self, exception_type, exception_value, exception_traceback):
        log.debug('Executing __exit__ method')
        if exception_value:
            self._connection.rollback()
            log.debug(f'An exception occurred: {exception_value}')
        else:
            self._connection.commit()
            log.debug('Transaction committed')
        self._cursor.close()
        Connection.release_connection(self._connection)
