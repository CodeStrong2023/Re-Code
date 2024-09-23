import logging as log


log.basicConfig(level=log.INFO,
                format='%(asctime)s:%(levelname)s [%(filename)s:%(lineno)s] %(message)s',
                datefmt='%I:%M:%S %p',
                handlers=[
                    log.FileHandler('data_layer.log', encoding='utf8'),
                    log.StreamHandler()
                ])
