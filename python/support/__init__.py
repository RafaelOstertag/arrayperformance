class StatisticsEntry(object):
    def __init__(self):
        self.min = None
        self.max = None
        self._data_points = []

    def add_data_point(self, val):
        if self.min is None:
            self.min = val
        else:
            self.min = min(self.min, val)

        if self.max is None:
            self.max = val
        else:
            self.max = max(self.max,val)

        self._data_points.append(val)

    def average(self):
        return round(
            float(sum(self._data_points)) / max(len(self._data_points),1)
            )

class Statistics(object):
    def __init__(self):
        self._statistics = {}
    
    def record(self, label, start, end):
        if label in self._statistics:
            statistics_entry = self._statistics[label]
        else:
            statistics_entry = StatisticsEntry()
            self._statistics[label] = statistics_entry

        statistics_entry.add_data_point(end-start)

    def dump_to_file(self, filename):
        with open(filename, "w") as f:
            for k,v in self._statistics.items():
                f.write("python-array;{};{};{};{}\n".format(k,v.min,v.average(),v.max))


class Log(object):
    def __init__(self, filename):
        self.filename = filename
        self._file = open(self.filename, "w")

    def close(self, *args):
        self._file.close()

    def write(self, label, iteration, start, end):
        self._file.write("python-array;{};{};{}\n".format(label, iteration, end-start))
