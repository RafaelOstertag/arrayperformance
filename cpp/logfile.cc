#include "logfile.hh"

LogFile::LogFile(const std::string& file) : _logfile{} {
    _logfile.open(file, std::ios::trunc);
}

void LogFile::write(const std::string& label, int iteration, long duration) {
    _logfile << "cpp-array"
             << ";" << label << ";" << iteration << ";" << duration
             << std::endl;
}

LogFile::~LogFile() { _logfile.close(); }