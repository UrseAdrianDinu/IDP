import configureStore from 'redux-mock-store';
import axios from 'axios';
import thunk from 'redux-thunk';
import sinon from 'sinon';

import administration, { getGatewayRoutes } from './administration.reducer';

describe('Administration reducer tests', () => {
  function isEmpty(element): boolean {
    if (element instanceof Array) {
      return element.length === 0;
    }
    return Object.keys(element).length === 0;
  }

  function testInitialState(state) {
    expect(state).toMatchObject({
      loading: false,
      errorMessage: null,
      totalItems: 0,
    });
    expect(isEmpty(state.gateway.routes));
  }

  function testMultipleTypes(types, payload, testFunction, error?) {
    types.forEach(e => {
      testFunction(administration(undefined, { type: e, payload, error }));
    });
  }

  describe('Common', () => {
    it('should return the initial state', () => {
      testInitialState(administration(undefined, { type: '' }));
    });
  });

  describe('Requests', () => {
    it('should set state to loading', () => {
      testMultipleTypes([getGatewayRoutes.pending.type], {}, state => {
        expect(state).toMatchObject({
          errorMessage: null,
          loading: true,
        });
      });
    });
  });

  describe('Failures', () => {
    it('should set state to failed and put an error message in errorMessage', () => {
      testMultipleTypes(
        [getGatewayRoutes.rejected.type],
        'something happened',
        state => {
          expect(state).toMatchObject({
            loading: false,
            errorMessage: 'error',
          });
        },
        {
          message: 'error',
        }
      );
    });
  });

  describe('Success', () => {
    it('should update state according to a successful fetch gateway routes request', () => {
      const payload = { data: [] };
      const toTest = administration(undefined, { type: getGatewayRoutes.fulfilled.type, payload });

      expect(toTest).toMatchObject({
        loading: false,
        gateway: { routes: payload.data },
      });
    });
  });
  describe('Actions', () => {
    let store;

    const resolvedObject = { value: 'whatever' };
    beforeEach(() => {
      const mockStore = configureStore([thunk]);
      store = mockStore({});
      axios.get = sinon.stub().returns(Promise.resolve(resolvedObject));
      axios.post = sinon.stub().returns(Promise.resolve(resolvedObject));
    });
    it('dispatches FETCH_GATEWAY_ROUTE_PENDING and FETCH_GATEWAY_ROUTE_FULFILLED actions', async () => {
      const expectedActions = [
        {
          type: getGatewayRoutes.pending.type,
        },
        {
          type: getGatewayRoutes.fulfilled.type,
          payload: resolvedObject,
        },
      ];
      await store.dispatch(getGatewayRoutes());
      expect(store.getActions()[0]).toMatchObject(expectedActions[0]);
      expect(store.getActions()[1]).toMatchObject(expectedActions[1]);
    });
  });
});
