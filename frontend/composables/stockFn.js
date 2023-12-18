import axios from "axios";
import {ref} from "vue";
import {useRouter} from "vue-router";

function stockFn() {

    const stocks = ref('');
    const router = useRouter();

    function getStocks() {
        axios.get('http://localhost:8080/api/stocks').then((result) => {
            stocks.value = result.data.data;
        });
    }

    function moveToDetailPage(id) {
        router.push(`${id}`)
    }

    function moveToRegistPage() {
        router.push(`regist`)
    }

    return {stocks, getStocks, moveToDetailPage, moveToRegistPage}
}

export { stockFn }