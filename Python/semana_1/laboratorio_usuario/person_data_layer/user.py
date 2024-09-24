class User:
    def __init__(self, user_id=None, username=None, password=None):
        self._user_id = user_id
        self._username = username
        self._password = password

    # methods getter and setter
    @property
    def user_id(self):
        return self._user_id

    @user_id.setter
    def user_id(self, user_id):
        self._user_id = user_id

    @property
    def username(self):
        return self._username

    @username.setter
    def username(self, username):
        self._username = username

    @property
    def password(self):
        return self._password

    @password.setter
    def password(self, password):
        self._password = password
