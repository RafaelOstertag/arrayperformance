DEFAULT_FLAGS = -Wall -Werror -O3 -pedantic -std=c++17
EXECUTABLE = cpp-array

all: $(EXECUTABLE)
clean:
	rm -f *.o
	rm -f $(EXECUTABLE)

.cc.o:
	$(CXX) $(DEFAULT_FLAGS) $(CXXFLAGS) -c -o $@ $<

$(EXECUTABLE): array.o logfile.o main.o
	$(CXX) $(DEFAULT_FLAGS) $(CXXFLAGS) -o $@ $^

.PHONY: clean