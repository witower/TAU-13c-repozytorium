#ifndef __PRODUCT__
#define __PRODUCT__

#include <string>
#include <iostream>

using namespace std;

struct product
{
    int id;
    string pname;
    int version;
};

inline bool operator==(const product &p1, const product &p2)
{
    return (p1.id == p2.id) && (p1.pname == p2.pname) && (p1.version == p2.version);
}

inline ostream &operator<<(std::ostream &out, const product &p)
{
    out << "{" << p.id << ", " << p.pname << ", " << p.version << "}";
    return out;
}

#endif