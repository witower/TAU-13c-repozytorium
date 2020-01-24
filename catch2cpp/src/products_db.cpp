#include "products_db.hpp"
#include "product.hpp"

using namespace std;

list<product> ProductsDatabase::getAllProducts()
{
    list<product> result;
    for (auto &e : productsDatabase)
    {
        result.push_back(e.second);
    }
    return result;
}

void ProductsDatabase::addProduct(const product &p)
{
    productsDatabase[p.id] = p;
}

product ProductsDatabase::getProductById(const int id)
{
    product p;
    for (auto &e : productsDatabase)
    {
        if (e.second.id == id)
        {
            p = e.second;
        }
    }
    return p;
}

void ProductsDatabase::deleteProduct(const int id)
{
    if (productsDatabase.count(id) == 0)
    {
        throw invalid_argument("Product not found");
    }
    productsDatabase.erase(id);
}
