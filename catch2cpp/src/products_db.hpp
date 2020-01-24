#ifndef __PRODUCTS_DATABASE_HPP__
#define __PRODUCTS_DATABASE_HPP__

#include "product.hpp"
#include <map>
#include <list>

using namespace std;

class ProductsDatabase
{
protected:
    map<int, product> productsDatabase;

public:
    list<product> getAllProducts();
    void addProduct(const product &p);
    product getProductById(const int id);
    void deleteProduct(const int id);
};

#endif