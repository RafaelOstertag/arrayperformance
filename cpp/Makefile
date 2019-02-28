DEFAULT_FLAGS = -Wall -Werror -O3 -pedantic -std=c++17
EXECUTABLE = cpp-array

all: cpp-array
clean:
	rm -f $(EXECUTABLE)

$(EXECUTABLE): array.cpp
	$(CXX) $(DEFAULT_FLAGS) $(CXXFLAGS) -o $@ $<

.PHONY: clean