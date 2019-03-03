package array

const (
	numberOfArrayElements = 1000000
)

type dataStruct struct {
	valueA int32
	valueB int32
	valueC int64
}

// Array holds the arrays used during benchmarking
type Array struct {
	structArray [numberOfArrayElements]dataStruct
	sumArray    [numberOfArrayElements]int64
}

// New pointer to Array
func New() *Array {
	return &Array{}
}

// FillStructArray fills the data array
func (a *Array) FillStructArray() {
	for i := int32(0); i < numberOfArrayElements; i++ {
		ds := &a.structArray[i]
		ds.valueA = i
		ds.valueB = i + 1
		ds.valueC = int64(i + 2)
	}
}

// FillSumArray fills the sum array by computing the sum of each dataStruct of structArray
func (a *Array) FillSumArray() {
	for i, ds := range a.structArray {
		a.sumArray[i] = int64(ds.valueA+ds.valueB) + ds.valueC
	}
}

// SumOfArray sums up all items in sumArray
func (a *Array) SumOfArray() int64 {
	var sum int64
	for _, s := range a.sumArray {
		sum += s
	}
	return sum
}
