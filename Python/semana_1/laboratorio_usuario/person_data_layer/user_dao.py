from person_data_layer.user import User
from person_data_layer.curso_from_pool import CursorFromPool
from person_data_layer.logger_base import log


class UserDAO:
    """
    DAO -> Data Access Object for the user table
    CRUD -> Create - Read - Update - Delete
    """

    _SELECT = 'SELECT * FROM "user" ORDER BY user_id'
    _INSERT = 'INSERT INTO "user"(username, password) VALUES (%s, %s)'
    _UPDATE = 'UPDATE "user" SET username=%s, password=%s WHERE user_id=%s'
    _DELETE = 'DELETE FROM "user" WHERE user_id=%s'

    @classmethod
    def select(cls):
        with CursorFromPool() as cursor:
            log.debug('Selecting users')
            cursor.execute(cls._SELECT)
            records = cursor.fetchall()
            users = []
            for record in records:
                user = User(record[0], record[1], record[2])
                users.append(user)
            return users

    @classmethod
    def insert(cls, user):
        with CursorFromPool() as cursor:
            log.debug(f'User to insert: {user}')
            values = (user.username, user.password)
            cursor.execute(cls._INSERT, values)
            return cursor.rowcount

    @classmethod
    def update(cls, user):
        with CursorFromPool() as cursor:
            log.debug(f'User to update: {user}')
            values = (user.username, user.password, user.user_id)
            cursor.execute(cls._UPDATE, values)
            return cursor.rowcount

    @classmethod
    def delete(cls, user):
        with CursorFromPool() as cursor:
            log.debug(f'User to delete: {user}')
            values = (user.user_id,)
            cursor.execute(cls._DELETE, values)
            return cursor.rowcount
