package logfile

import (
	"fmt"
	"os"
	"time"
)

type Logfile struct {
	file *os.File
}

// New log file
func New(fileName string) *Logfile {
	file, err := os.Create(fileName)
	if err != nil {
		panic(err)
	}

	return &Logfile{file}
}

func (l *Logfile) Write(label string, i int, start, end time.Time) {
	duration := end.Sub(start).Nanoseconds()
	msg := fmt.Sprintf("go-array;%s;%v;%v\n", label, i, duration)

	if _, err := l.file.WriteString(msg); err != nil {
		panic(err)
	}
}

func (l *Logfile) Close() {
	l.file.Close()
}
