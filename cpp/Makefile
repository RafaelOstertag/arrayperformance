DEFAULT_FLAGS = -Wall -Werror -O3 -pedantic -std=c++17

all: locality-cpp
clean:
	rm -f locality-cpp

locality-cpp: locality.cpp
	$(CXX) $(DEFAULT_FLAGS) $(CXXFLAGS) -o $@ $<


.PHONY: clean