#ifndef _LOGFILE_HH
#define _LOGFILE_HH

#include <fstream>
#include <string>

class LogFile {
   public:
    LogFile(const std::string& file);
    ~LogFile();
    LogFile(const LogFile&) = delete;
    LogFile(LogFile&&) = delete;
    LogFile& operator=(const LogFile&) = delete;
    LogFile& operator=(LogFile&&) = delete;

    void write(const std::string& label, int iteration, long duration);

   private:
    std::ofstream _logfile;
};

#endif