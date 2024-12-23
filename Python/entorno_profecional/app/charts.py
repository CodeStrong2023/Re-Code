import matplotlib.pyplot as plt


def generate_bar_chart(name, labels, values):
    fig, ax = plt.subplots()
    ax.bar(labels, values)
    plt.savefig(f'./img/{name}.png')
    plt.close()


def generate_pie_chart(labels, values):
    fig, ax = plt.subplots()
    ax.pie(values, labels=labels)
    ax.axis('equal')
    plt.savefig("pie_chart.png")
    plt.close()


if __name__ == '__main__':
    keys = ['a', 'b', 'c']
    vals = [10, 40, 800]
    generate_pie_chart(keys, vals)

# this is a test of actualization docker container
# this is another test of actualization docker container
