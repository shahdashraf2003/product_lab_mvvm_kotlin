import android.content.Context
import com.example.mvvm.data.database.ProductDataBase
import com.example.mvvm.data.product.model.Product


class ProductLocalDataSource(
   context: Context
){
    private val productDao =
        ProductDataBase.getInstance(context).getProductDao()

    suspend fun insertProduct(product : Product) = productDao.insertProduct(product)

    suspend fun deleteProduct(product : Product) = productDao.deleteProduct(product)

    suspend fun getAllProducts() = productDao.getAllProducts()


}