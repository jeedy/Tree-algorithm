Tree-algorithm
==============

트리구조 개념을 가지고 만든 알고리즘, 마지막 리프 개수를 입력하면 특정 조건에 만족하는 모든 트리들을 구하고 각 트리에서 모든 리프 노드의 레벨을 출력하는 로직.

# 트리(tree)의 정의

트리(tree)는 하나 이상의 노드(node)로 이루어진 유한 집합으로서

(1) 노드 중에는 루트(root)라고 하는 노드가 하나 있고

(2) 나머지 노드들은 n(≥0)개의 분리 집합 T1, …, TN으로 분할 될 수 있다.

여기서 T1, …, TN은 각각 하나의 트리이며, 루트의 서브트리(subtree)라고 한다.

(트리는 사이클이 없는 그래프(acyclic graph)이며 계층 구조를 이룬다)

![이진트리](https://lh5.googleusercontent.com/-IGnNCdd43iM/UvRCYM2dmfI/AAAAAAAAA5g/NukVGw36cwM/w512-h350-no/tree.PNG)

노드(node)란 한 정보 아이템에다 이것으로부터 다른 노드로 뻗어진 가지를 합쳐서 말한다. 위 

트리는 열세 개의 노드를 가지는데, 각 정보 아이템은 편의상 알파벳 한 글자로 나타내고 있다. 여기서 

루트(root)는 A인데, 보통 트리의 맨 꼭대기에 그린다.

어떤 노드의 서브트리의 수를 그 노드의 차수(degree)라고 한다. A의 차수는 3이고, C의 차수는 

1, F의 차수는 0이다. 차수가 0인 노드를 리프(leaf) 또는 단말(terminal) 노드라고 하고 그 외의 

나머지 노드들을 내부(internal) 또는 비단말(non-terminal) 노드라고 한다. {K, L, F, G, M, I, J}는 

단말 노드들의 집합니다. 한 노드 X의 서브트리의 루트를 X의 자식(child)이라 하며 X는 그 자식의 

부모(parent)이다. D의 자식은 H, I, J이며 D의 부모는 A이다. 부모가 같은 자식들은 형제(sibling)라 

한다. H, I, J는 형제들이다. M의 조부는 D인데, 이러한 방법으로 가족 어휘를 확장할 수 있다. 트리의 

차수(degree of tree)란 그 트리에 있는 노드의 최대 차수이며 위 트리의 차수가 3이다. 한 노드의 

조상(ancestors)이라 하면 루트에서부터 그 노드에 이르는 경로상에 있는 모든 노드들을 말하는데, M의 

조상들은 A, D, H 이다.

노드의 레벨(level)은 기본적으로 루트의 레벨을 1로 가정한 후에 정의된다. 만일 한 노드의 레벨이 

i이면 자식의 레벨은 i+1이 된다. 트리의 높이 또는 깊이(depth of tree)란 그 트리에 속한 노드의 최대 

레벨을 말한다. 위 트리는 깊이가 4이다.

자세한 내용은 [링크](http://ko.wikipedia.org/wiki/%EC%9D%B4%EC%A7%84_%ED%8A%B8%EB%A6%AC) 를 통해.

# 다음 조건을 만족하는 알고리즘이다.

(1) 전체 노드의 개수는 하나 이상이다.

(2) 리프 노드가 아닌 모든 노드, 즉 내부 노드들의 차수는 모두 2이다.

(3) 트리의 어떤 노드의 왼쪽 서브트리의 깊이를 ld, 오른쪽 서브트리의 깊이를 rd라고 하면 ld ≤ rd 이다.


```
작성하고자 하는 프로그램은 사용자가 트리의 리프 노드의 개수를 입력하면 위 조건에 만족하는 모든 트리들의 구할 수 있고,
각 트리에서 모든 리프 노드의 레벨을 출력한다.
리프 노드의 레벨을 출력하는 순서는 트리를 그렸을 때 맨 왼쪽 리프 노드에서부터 우측 리프 노드의 순서대로 출력합니다.
트리가 여러 개일 경우는 오름차순으로 출력합니다.
```

![예제1](https://lh4.googleusercontent.com/-TxKCwja9FkY/UvRK5L53ZvI/AAAAAAAAA7M/PYIKwTl2oao/w603-h259-no/%25EC%25BA%25A1%25EC%25B2%25981.PNG)

![예제2](https://lh5.googleusercontent.com/-44q5mtBo3MU/UvRK5JzdszI/AAAAAAAAA7Q/c8QSd2ivems/w597-h544-no/%25EC%25BA%25A1%25EC%25B2%25982.PNG)
