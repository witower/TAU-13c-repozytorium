all: test

SOURCES    := ${wildcard src/*.cpp}
OBJECTS    := ${SOURCES:.cpp=.o}

SOURCES_TEST    := ${wildcard tests/*.cpp}
OBJECTS_TEST    := ${SOURCES_TEST:.cpp=.o}

test: tests_run
	./tests_run

tests_run: $(OBJECTS) $(OBJECTS_TEST)
	g++ -std=c++17 $^ -o $@

tests/%.o: tests/%.cpp
	gcc -std=c++17 -Isrc -Itests -c $< -o $@

src/%.o: src/%.cpp
	gcc -std=c++17 -Isrc -Itests -c $< -o $@

clean:
	rm -f tests/*.o src/*.o tests_run
