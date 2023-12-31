import { actionType } from "../constants/index";
import { axiosCategories } from "../api/axios";

export const getCategoriesList = async (dispatch) => {
    dispatch({ type: actionType.CATEGORY_LIST_REQUEST });
    try {
        const { data } = await axiosCategories.get('getCategoriesList');
        dispatch({ type: actionType.CATEGORY_LIST_SUCCESS, payload: data })
    } catch (error) {
        dispatch({
            type: actionType.CATEGORY_LIST_FAIL,
            payload: error.message
        });
    }
};

export const getCategoryProducts = async (dispatch, categoryKey) => {
    dispatch({ type: actionType.CATEGORY_PRODUCT_REQUEST });
    try {
        const { data } = await axiosCategories.get(`findByCategoryName/${categoryKey}`);
        dispatch({
            type: actionType.CATEGORY_PRODUCT_SUCCESS, payload: data
        });
    } catch (error) {
        dispatch({
            type: actionType.CATEGORY_PRODUCT_FAIL,
            payload: error.message
        });
    }
};