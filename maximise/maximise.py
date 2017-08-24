import numpy as np

def p_to_g(p, b_width):
    """Provides mapping from phenotype space to genotype space.

    Args:
        p (int array): Int array representing phenotype space.
        b_width (int): required binary width.

    Returns:
        str array: Return array of binary strings.
    """
    g = []

    for item in p:
        g.append(den_to_bin(item, width=b_width))

    return g

def bin_to_den(b_val):
    """Takes binary string and return its denary value.

    Args:
        b_val (str): binary string.

    Returns:
        int: Return integer.
    """
    return int(b_val, 2)


def den_to_bin(d_val, b_width):
    """Takes denary value and return binary of given width.

    Args:
        d_val (int): denary value.
        b_width (int): required binary width.

    Returns:
        str: Return binary string of given width.
    """
    return np.binary_repr(d_val, width=b_width)

def g_to_p(g):
    """Provide mapping from genotype space to phenotype space.

    Args:
        g (str): Binary string.

    Returns:
        int: Coresponding integer value.
    """
    p = []

    for item in g:
        p.append(bin_to_den(item, 2))

    return p

def crossover(item1, item2):
    """Takes two parents, crossover them in random point and returns two childs.

    Args:
        item1 (str): First parent binary string.
        item2 (str): Second parent binary string.

    Returns:
        tuple(str, str): Return tuple containing two binary strings.
    """
    point = np.random.randint(len(item1)) #select crossover point

    # childs will be created by combining head of one parent and tail from another
    # in this oparation two childs are created
    child1 = item1[:point] + item2[point:]
    child2 = item2[:point] + item1[point:]

    return child1, child2

def mutate(item):
    """Toggle randomly selected gene.

    Args:
        item (str): Binary string representing individual.

    Returns:
        str: Return mutated individual.
    """
    point = np.random.randint(len(item)) #select gene to mutate

    mutation = '1' if item[point] == '0' else '0' #reverse gene value

    return item[:point] + mutation + item[point+1:]

def select(p):
    """Select parents from population.

    Args:
        p (int array): Current population.

    Returns:
        int array: Return tuple containing selected individuals.
    """
    parent_num = int(np.ceil(float(len(p)) / 5)) # calculate 20% of population
    parent_num = parent_num if parent_num % 2 == 0 else parent_num + 1
    indexes = np.random.choice(range(len(p)), parent_num, replace=False)

    return p[indexes]

def replace(p, c1, c2):
    p = np.concatenate((p, [int(c1, 2), int(c2, 2)]))
    s_values = np.sort(p)

    for i in xrange(2):
        s_values = np.delete(s_values, 0)

    return s_values

# initial population
population = np.array([1,5,8,2,15,7,12,6])

for i in xrange(1, 20):
#     genotypes_space = p_to_g(population)
    selected = select(population)
    # print selected
    for j in range(0, len(selected), 2):
        parent1 = den_to_bin(selected[j], 8)
        parent2 = den_to_bin(selected[j+1], 8)

        c1, c2 = crossover(parent1, parent2)
        c1 = mutate(c1)
        population = replace(population, c1, c2)

    print 'After {} iteration population: {}'.format(i, population)

print 'Final population: {}'.format(population)
