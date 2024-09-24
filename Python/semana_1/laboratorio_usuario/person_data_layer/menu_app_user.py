from person_data_layer.user import User
from person_data_layer.user_dao import UserDAO
from logger_base import log

option = None
while option != 5:
    print('Options:')
    print('1. List users')
    print('2. Add user')
    print('3. Update user')
    print('4. Delete user')
    print('5. Exit')
    option = int(input('Enter an option (1-5): '))

    if option == 1:
        users = UserDAO.select()
        for user in users:
            log.info(user)

    elif option == 2:
        username_var = input('Enter the username: ')
        password_var = input('Enter the password: ')
        user = User(username=username_var, password=password_var)
        user_inserted = UserDAO.insert(user)
        log.info(f'User inserted: {user_inserted}')

    elif option == 3:
        user_id_var = int(input('Enter the user ID to update: '))
        username_var = input('Enter the new username: ')
        password_var = input('Enter the new password: ')
        user = User(user_id=user_id_var, username=username_var, password=password_var)
        user_updated = UserDAO.update(user)
        log.info(f'User updated: {user_updated}')

    elif option == 4:
        user_id_var = int(input('Enter the user ID to delete: '))
        user = User(user_id=user_id_var)
        user_deleted = UserDAO.delete(user)
        log.info(f'User deleted: {user_deleted}')
else:
    log.info('Exiting the application, see you soon!!!')
