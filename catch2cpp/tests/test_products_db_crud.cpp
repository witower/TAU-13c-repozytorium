#include "catch.hpp"
#include "../src/products_db.hpp"
#include <map>
#include <list>
#include <iostream>

using namespace std;
using namespace Catch::Matchers;

class TestDatabase : public ProductsDatabase
{
public:
    void setDb(map<int, product> d)
    {
        productsDatabase = d;
    };
};

TEST_CASE("Basic database object operations", "[ProductsDatabase][constructors]")
{
  SECTION("The database object can be created")
  {
    REQUIRE_NOTHROW([]() { ProductsDatabase db; });
  }
}

TEST_CASE("Getting all data from the database", "[ProductsDatabase][getAllProducts]")
{
    SECTION("GetAllProducts method is present")
    {
        REQUIRE_NOTHROW([]() {ProductsDatabase db; db.getAllProducts(); }());
    }

    SECTION("GetAllProducts method should return empty list when database is empty")
    {
        ProductsDatabase db;
        list<product> result = db.getAllProducts();
        REQUIRE(result == list<product>{});
    }

    SECTION("GetAllProducts method should return all products from database")
    {
        TestDatabase db;
        map<int, product> expected =
            {
                {1, {1, "Kurtka Jack", 17}},
                {2, {2, "Kurtka Jenny", 4}},
                {3, {3, "Kurtka Jack", 16}}};
        db.setDb(expected);
        list<product> expected_list;
        for (auto e : expected)
        {
            expected_list.push_back(e.second);
        }
        REQUIRE(db.getAllProducts() == expected_list);
    }
}

TEST_CASE("Adding data to the database", "[ProductsDatabase][addProduct]")
{
    SECTION("The database object can be created")
    {
        TestDatabase db;
        map<int, product> allProducts =
            {
                {1, {1, "Kurtka Jack", 17}},
                {2, {2, "Kurtka Jenny", 4}}};
        db.setDb(allProducts);
        list<product> all_products_list;
        for (auto e : allProducts)
        {
            all_products_list.push_back(e.second);
        }
        REQUIRE(db.getAllProducts() == all_products_list);

        SECTION("AddProduct method should add a product to the database")
        {
            REQUIRE_NOTHROW(db.addProduct({3, "Kurtka Jack", 16}));

            SECTION("The database should contain element with the title Persepolis")
            {
                all_products_list.push_back({3, "Kurtka Jack", 16});
                REQUIRE(db.getAllProducts() == all_products_list);
            }
        }
    }
}

TEST_CASE("Getting data from database by id", "[ProductsDatabase][getProductById]")
{
    SECTION("The database object can be created")
    {
        TestDatabase db;
        map<int, product> allProducts =
            {
                {1, {1, "Kurtka Jack", 17}},
                {2, {2, "Kurtka Jenny", 4}},
                {3, {3, "Kurtka Jack", 16}}};
        db.setDb(allProducts);

        SECTION("GetProductById method should get a product with id 2")
        {
            REQUIRE_NOTHROW(db.getProductById(2));

            SECTION("database return product with id 2")
            {
                product product_to_compare = {2, "Kurtka Jenny", 4};
                REQUIRE(db.getProductById(2) == product_to_compare);
            }
        }
    }
}

SCENARIO("Deleting data from database", "[ProductsDatabase][bdd][deleteProduct]")
{
    GIVEN("We have some data in database")
    {
        TestDatabase db;
        map<int, product> allProducts =
            {
                {1, {1, "Kurtka Jack", 17}},
                {2, {2, "Kurtka Jenny", 4}},
                {3, {3, "Kurtka Jack", 16}}};
        db.setDb(allProducts);
        list<product> all_products_list;
        for (auto e : allProducts)
        {
            all_products_list.push_back(e.second);
        }
        CHECK(db.getAllProducts() == all_products_list);

        WHEN("We remove product with id 2 from the database")
        {
            REQUIRE_NOTHROW(db.deleteProduct(2));
            THEN("The database shouldn't contain a product with id 2")
            {
                for (auto p : db.getAllProducts())
                {
                    CHECK(p.id != 2);
                }
            }
        }

        WHEN("We try to remove product that doesn't exist in the database")
        {
            THEN("The exception should be thrown")
            {
                REQUIRE_THROWS_AS(db.deleteProduct(10), invalid_argument);
            }

            THEN("The exception should contain suitable message")
            {
                REQUIRE_THROWS_WITH(db.deleteProduct(10), EndsWith("not found") || StartsWith("product"));
            }
        }
    }
}
