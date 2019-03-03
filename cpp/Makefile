DEFAULT_FLAGS = -Wall -Werror -O3 -pedantic -std=c++17
EXECUTABLE = cpp-array
OBJFILES = array.o logfile.o stat.o main.o

all: $(EXECUTABLE)
clean:
	rm -f *.o
	rm -f $(EXECUTABLE)

.cc.o:
	$(CXX) $(DEFAULT_FLAGS) $(CXXFLAGS) -c -o $@ $<

$(EXECUTABLE): $(OBJFILES)
	$(CXX) $(DEFAULT_FLAGS) $(CXXFLAGS) -o $@ $^

.PHONY: clean